package service.impl.validation;

import model.Order;
import service.api.Validator;

public class OrderQuantityValidator extends Validator<Order> {
    @Override
    public void validate(Order order) {
        if (order.getProductCount() < 1) throw new RuntimeException("Product count must be greater than 0");
        this.checkNext(order);
    }
}
