package service.impl;

import dao.api.OrderRepository;
import model.Order;
import service.api.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order entity) {
        return repository.create(entity);
    }

    @Override
    public Optional<Order> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Order entity) {
        repository.delete(entity);
    }
}
