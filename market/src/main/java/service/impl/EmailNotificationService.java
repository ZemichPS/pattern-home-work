package service.impl;

import model.Customer;
import service.api.EmailService;
import service.api.NotificationService;

import javax.mail.*;

public class EmailNotificationService implements NotificationService {
    private final EmailService emailService;

    public EmailNotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean notify(Customer customer, String subject, String text) throws MessagingException {
        emailService.send(customer.email(), subject, text);
        return true;
    }
}
