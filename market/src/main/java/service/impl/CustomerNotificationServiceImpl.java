package service.impl;

import model.Customer;
import model.Notification;
import model.OrderDetails;
import service.impl.notificationHanddlers.NotificationHandler;
import service.api.CustomerNotificationService;
import service.api.crud.CustomerService;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

public class CustomerNotificationServiceImpl implements CustomerNotificationService {
    private final List<NotificationHandler<Customer, Notification>> notificationHandlers;
    private final CustomerService customerService;

    public CustomerNotificationServiceImpl(
            List<NotificationHandler<Customer, Notification>> notificationHandlers,
            CustomerService customerService
    ) {
        this.notificationHandlers = notificationHandlers;
        this.customerService = customerService;
    }

    @Override
    public void notify(OrderDetails orderDetails) {
        UUID customerUuid = orderDetails.getCustomer().getUuid();
        Customer customer = customerService.getById(customerUuid).orElseThrow(() -> new RuntimeException("Customer not found"));
        notificationHandlers.forEach(service -> {
            Notification notification = createNotification(orderDetails);
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

    private Notification createNotification(OrderDetails orderDetails) {
        String notificationBody = switch (orderDetails.getStatus()) {
            case NEW -> "OrderDetails has been created";
            case CANCELED -> "OrderDetails has been canceled";
            case DELIVERED -> "OrderDetails has been delivered";
        };

        String subject = "OrderDetails status changed";
        return new Notification(subject, notificationBody);
    }
}
