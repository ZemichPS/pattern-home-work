package model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID uuid;
    private UUID userUuid;
    private final List<Product> productList = new ArrayList<>();
    private Status status;

    public Order(UUID uuid, UUID userUuid, Status status) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.status = status;
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


    public int getProductCount() {
        return productList.size();
    }


    public Status getStatus() {
        return status;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
