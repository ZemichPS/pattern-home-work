package service.impl.validation;

import model.Order;
import model.Status;
import service.api.Validator;

public class OrderActiveValidator extends Validator<Order> {
    @Override
    public void validate(Order order) {
        if (order.getStatus().equals(Status.CANCELED)) throw new RuntimeException("Order status can't be canceled");
        checkNext(order);
    }
}
