package dao.impl;

import dao.api.OrderRepository;
import model.Order;

import java.util.List;
import java.util.UUID;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public Order getById(UUID id) {
        return null;
    }

    @Override
    public void delete(Order id) {

    }
}
