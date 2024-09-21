package service.impl;

import model.Order;
import model.User;
import service.api.NotificationService;
import service.api.OrderService;
import service.api.UserService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderServiceFacade {

    private static final Logger logger = Logger.getLogger(OrderServiceFacade.class.getName());

    private final UserService userService;
    private final OrderService orderService;
    private final List<NotificationService> notificationServices;

    public OrderServiceFacade(UserService userService, OrderService orderService,
                              List<NotificationService> notificationServices
    ) {
        this.userService = userService;
        this.orderService = orderService;
        this.notificationServices = notificationServices;
    }

    public Order createAndNotify(Order order) {
        orderService.save(order);
        String subject = "Order created";
        String text = "Order id: " + order.uuid();
        notify(order, subject, text);
        return order;
    }

    public void cancelAndNotify(Order order) {
        orderService.delete(order);
        UUID orderUuid = order.uuid();
        String subject = "Order canceled";
        String text = String.format("Order id: %s was canceled", orderUuid);
        notify(order, subject, text);
    }

    public void updateAndNotify(Order order) {
        orderService.delete(order);
        UUID orderUuid = order.uuid();
        String subject = "Order updated";
        String text = String.format("Order id: %s was updated", orderUuid);
        notify(order, subject, text);
    }

    public List<Order> getAll(){
        return orderService.getAll();
    }

    private void notify(Order order, String subject, String text) {
        UUID userUuid = order.uuid();
        User user = userService.getById(userUuid).orElseThrow(() -> new RuntimeException("User not found"));
        notificationServices.forEach(service -> {
            try {
                service.notify(user, subject, text);
            } catch (MessagingException e) {
                String errorMessage = String.format("Error notify user: %s, notify service: %s. Cause: %s",
                        user.uuid(), service.getClass().getSimpleName(), e.getMessage());
                logger.severe(errorMessage);
                throw new RuntimeException(errorMessage);
            }
        });
    }
}
