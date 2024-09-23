package service.impl.validation.order;

import model.Order;
import model.Status;
import service.impl.validation.Validator;

public class OrderActiveValidator extends Validator<Order> {
    @Override
    public void validate(Order validated) {
        if (validated.getStatus().equals(Status.CANCELED)) throw new RuntimeException("Order status can't be canceled");
        checkNext(validated);
    }
}
