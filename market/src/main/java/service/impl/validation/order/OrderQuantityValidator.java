package service.impl.validation.order;

import model.Order;
import service.impl.validation.Validator;

public class OrderQuantityValidator extends Validator<Order> {
    @Override
    public void validate(Order validated) {
        if (validated.getProductCount() < 1) throw new RuntimeException("Product count must be greater than 0");
        this.checkNext(validated);
    }
}
