package service.impl;

import model.Customer;
import service.api.NotificationService;

public class SmsNotificationServiceImpl implements NotificationService {
    @Override
    public boolean notify(Customer customer, String subject, String text) {
        System.out.println("Customer %s was notified by SMS way. Subject %s: ".formatted(customer.name(), subject));
        return true;
    }
}
