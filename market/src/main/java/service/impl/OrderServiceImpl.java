package service.impl;

import dao.api.OrderRepository;
import model.Order;
import service.api.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final EventManager eventManager;

    public OrderServiceImpl(OrderRepository repository, EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    @Override
    public Order save(Order order) {
        eventManager.publish("ORDER_CREATED", order);
        return repository.create(order);
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
