package service.impl;

import model.Order;
import model.Customer;
import service.api.NotificationService;
import service.api.OrderService;
import service.api.CustomerService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderServiceFacade {

    private static final Logger logger = Logger.getLogger(OrderServiceFacade.class.getName());

    private final CustomerService customerService;
    private final OrderService orderService;
    private final List<NotificationService> notificationServices;

    public OrderServiceFacade(CustomerService customerService, OrderService orderService,
                              List<NotificationService> notificationServices
    ) {
        this.customerService = customerService;
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
        Customer customer = customerService.getById(userUuid).orElseThrow(() -> new RuntimeException("Customer not found"));
        notificationServices.forEach(service -> {
            try {
                service.notify(customer, subject, text);
            } catch (MessagingException e) {
                String errorMessage = String.format("Error notify customer: %s, notify service: %s. Cause: %s",
                        customer.uuid(), service.getClass().getSimpleName(), e.getMessage());
                logger.severe(errorMessage);
                throw new RuntimeException(errorMessage);
            }
        });
    }
}
