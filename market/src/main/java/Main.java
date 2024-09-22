import model.Category;
import model.Order;
import model.Product;
import model.Status;
import service.api.Validator;
import service.impl.validation.OrderActiveValidator;
import service.impl.validation.OrderQuantityValidator;
import service.impl.validation.OrderUserUuidNotNullValidator;

import java.math.BigDecimal;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(UUID.randomUUID(), null, Status.NEW);
        order.addProduct(new Product(UUID.randomUUID(), Category.BOOKS, "Война и мир", BigDecimal.TEN, true));
        order.addProduct(new Product(UUID.randomUUID(), Category.CLOTHING, "носочки", BigDecimal.ONE, true));
        order.addProduct(new Product(UUID.randomUUID(), Category.BOOKS, "1982", BigDecimal.ONE, false));


        Validator<Order> validator = new OrderActiveValidator();
        validator.linkWith(new OrderQuantityValidator())
                .linkWith(new OrderUserUuidNotNullValidator());

        validator.validate(order);

    }
}
