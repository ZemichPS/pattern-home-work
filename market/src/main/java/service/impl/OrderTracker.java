package service.impl;

import model.Notification;
import model.OrderDetails;
import service.api.EmailService;
import service.api.EventListener;

import javax.mail.MessagingException;

public class OrderTracker implements EventListener<OrderDetails> {

    private final String adminEmail;
    private final EmailService<Notification> emailService;

    public OrderTracker(String adminEmail, EmailService emailService) {
        this.adminEmail = adminEmail;
        this.emailService = emailService;
    }

    @Override
    public void update(String eventType, OrderDetails observable) {
        try {
            Notification notification = new Notification("OrderDetails status has been changed", observable.toString());
            emailService.send(adminEmail, notification);
        } catch (MessagingException e) {
            throw new RuntimeException("Error sending email", e);
        }
    }
}
