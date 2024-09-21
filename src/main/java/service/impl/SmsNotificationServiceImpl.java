package service.impl;

import model.User;
import service.api.NotificationService;

public class SmsNotificationServiceImpl implements NotificationService {
    @Override
    public boolean notify(User user, String subject, String text) {
        return false;
    }
}
