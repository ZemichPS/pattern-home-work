package service.api;

import model.User;

import javax.mail.MessagingException;

public interface NotificationService {
    boolean notify(User user, String subject, String text) throws MessagingException;
}
