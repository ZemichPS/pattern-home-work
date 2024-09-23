package service.impl;

import dao.impl.OrderPersistenceRepository;
import model.Order;
import service.api.crud.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderPersistenceRepository repository;

    public OrderServiceImpl(OrderPersistenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return repository.create(order);
    }

    @Override
    public Order update(Order entity) {
        return repository.update(entity);
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
