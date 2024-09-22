package service.api;

import model.Customer;

import javax.mail.MessagingException;

public interface NotificationService {
    boolean notify(Customer customer, String subject, String text) throws MessagingException;
}
