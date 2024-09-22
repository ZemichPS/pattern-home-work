package service.impl;

import model.Product;
import service.api.CustomerService;
import service.api.EmailService;
import service.api.EventListener;

import javax.mail.MessagingException;
import java.math.RoundingMode;

public class ProductOnSaleEmailNotificatorListener implements EventListener<Product> {
    private final EmailService emailService;
    private final CustomerService customerService;

    public ProductOnSaleEmailNotificatorListener(EmailService emailService,
                                                 CustomerService customerService) {
        this.emailService = emailService;
        this.customerService = customerService;
    }


    @Override
    public void update(String eventType, Product observable) {
        customerService.getAll().forEach(customer -> {
            String message;
            if (eventType.equals("NEW_PRODUCT_ON_SALE"))
                message = "Great news! We have new products at discounts. Product: %s. Price %s "
                        .formatted(observable.name(), observable.price().setScale(2, RoundingMode.DOWN));
            else
                message = "Great news! Great news! We have reduced the prices".formatted(observable.name(), observable.price().setScale(2, RoundingMode.DOWN));

            try {
                emailService.send(customer.email(), "Sale!!!", message);
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }
        });
    }
}
