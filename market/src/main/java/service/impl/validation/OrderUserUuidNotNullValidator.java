package service.impl.validation;

import model.Order;
import service.api.Validator;

import java.util.Objects;

public class OrderUserUuidNotNullValidator extends Validator<Order> {
    @Override
    public void validate(Order order) {
        if (Objects.isNull(order.getUserUuid())) throw new RuntimeException("User uuid is null");
        checkNext(order);
    }
}
