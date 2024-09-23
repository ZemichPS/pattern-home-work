package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {
    @Getter
    @Setter
    @Id
    private UUID uuid;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "order"
    )
    @JoinColumn(name = "customer_uuid", referencedColumnName = "uuid")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Getter
    @Setter
    private Status status;

    public Order addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        return this;
    }

    public Order removeOrderItem(OrderItem orderItem) {
        orderItem.setOrder(null);
        orderItems.remove(orderItem);
        return this;
    }

    public BigDecimal calculateTotalPrice() {
        return orderItems.stream()
                .map(orderItem -> {
                    BigDecimal price = orderItem.getProduct().getPrice();
                    return price.multiply(new BigDecimal(orderItem.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getProductCount() {
        return orderItems.size();
    }
}
