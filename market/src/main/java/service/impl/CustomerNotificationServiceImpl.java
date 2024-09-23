package service.impl;

import model.Customer;
import model.Notification;
import model.Order;
import service.impl.notificationHanddlers.NotificationHandler;
import service.api.CustomerNotificationService;
import service.api.crud.CustomerService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

public class CustomerNotificationServiceImpl implements CustomerNotificationService {
    private final List<NotificationHandler> notificationHandlers;
    private final CustomerService customerService;

    public CustomerNotificationServiceImpl(
            List<NotificationHandler> notificationHandlers,
            CustomerService customerService
    ) {
        this.notificationHandlers = notificationHandlers;
        this.customerService = customerService;
    }

    @Override
    public void notify(Order order) {
        UUID customerUuid = order.getCustomer().getUuid();
        Customer customer = customerService.getById(customerUuid).orElseThrow(() -> new RuntimeException("Customer not found"));
        notificationHandlers.forEach(service -> {
            Notification notification = createNotification(order);
            try {
                service.notify(customer, notification);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addCustomerNotificationHandler(NotificationHandler notificationHandler) {
        notificationHandlers.add(notificationHandler);
    }

    private Notification createNotification(Order order) {
        String notificationBody = switch (order.getStatus()) {
            case NEW -> "Order has been created";
            case CANCELED -> "Order has been canceled";
            case DELIVERED -> "Order has been delivered";
        };

        String subject = "Order status changed";
        return new Notification(subject, notificationBody);
    }
}
