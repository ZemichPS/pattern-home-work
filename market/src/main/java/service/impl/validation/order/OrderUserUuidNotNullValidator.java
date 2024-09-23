package service.impl.validation.order;

import model.Order;
import service.impl.validation.Validator;

import java.util.Objects;

public class OrderUserUuidNotNullValidator extends Validator<Order> {
    @Override
    public void validate(Order validated) {
        if (Objects.isNull(validated.getCustomer())) throw new RuntimeException("User uuid is null");
        checkNext(validated);
    }
}
