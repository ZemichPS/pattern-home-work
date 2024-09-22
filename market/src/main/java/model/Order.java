package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID uuid;
    private UUID userUuid;
    private final List<Product> productList = new ArrayList<>();
    private Status status;

    public Order() {
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public BigDecimal totalPrice() {
        return productList.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Order{" +
                "uuid=" + uuid +
                ", userUuid=" + userUuid +
                ", productList=" + productList +
                '}';
    }


}
