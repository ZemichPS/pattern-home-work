package service.impl;

import model.Order;
import service.api.CustomerNotificationService;
import service.api.crud.OrderService;
import service.impl.validation.Validator;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class OrderServiceFacade {

    private final OrderService orderService;
    private final EventManager eventManager;
    private final CustomerNotificationService customerNotificationService;
    private final Validator<Order> validator;

    public OrderServiceFacade(
            OrderService orderService,
            EventManager eventManager,
            CustomerNotificationService customerNotificationService, Validator<Order> validator) {
        this.orderService = orderService;
        this.eventManager = eventManager;
        this.customerNotificationService = customerNotificationService;
        this.validator = validator;
    }

    public Order createAndNotify(Order order) {
        validator.validate(order);
        orderService.save(order);
        customerNotificationService.notify(order);
        eventManager.publish(order.getStatus().name(), order);
        return order;
    }

    public void cancelAndNotify(Order order) {
        orderService.delete(order);
        customerNotificationService.notify(order);
        eventManager.publish(order.getStatus().name(), order);
    }

    public void updateAndNotify(Order order) {
        orderService.delete(order);
        UUID orderUuid = order.getUuid();
        customerNotificationService.notify(order);
        eventManager.publish(order.getStatus().name(), order);
    }

    public List<Order> getAll() {
        return orderService.getAll();
    }

}
