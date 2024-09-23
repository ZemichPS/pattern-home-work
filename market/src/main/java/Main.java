import model.Category;
import model.Order;
import model.Product;
import model.Status;
import service.impl.validation.Validator;
import service.impl.validation.order.OrderActiveValidator;
import service.impl.validation.order.OrderQuantityValidator;
import service.impl.validation.order.OrderUserUuidNotNullValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Validator<Order> validator = new OrderActiveValidator();
        validator.linkWith(new OrderQuantityValidator())
                .linkWith(new OrderUserUuidNotNullValidator());
//
//        validator.validate(null);

    }
}
