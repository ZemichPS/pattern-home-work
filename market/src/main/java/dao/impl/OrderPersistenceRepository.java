package dao.impl;


import model.Order;

import java.util.UUID;


public class OrderPersistenceRepository extends AbstractPersistenceRepository<Order, UUID> {

    public OrderPersistenceRepository() {
        super(Order.class);
    }

}
