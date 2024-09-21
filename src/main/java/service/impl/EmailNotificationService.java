package service.impl;

import model.User;
import service.api.NotificationService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotificationService implements NotificationService {
    private String host = "smtp.gmail.com";
    private String port = "587";
    final String username = "your-email@gmail.com"; // ваша почта
    final String password = "your-password";

    @Override
    public boolean notify(User user, String subject, String text) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username)); // Отправитель
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.email())); // Получатель
        message.setSubject(subject);
        message.setText(text);

        Transport.send(message);
        System.out.println("Письмо успешно отправлено!");
        return true;
    }
}
