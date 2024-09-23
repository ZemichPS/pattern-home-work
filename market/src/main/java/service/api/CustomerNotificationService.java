package service.api;

import model.Order;

public interface CustomerNotificationService {
    void notify(Order order);
}
