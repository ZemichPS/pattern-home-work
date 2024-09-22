package service.impl;

import dao.api.CustomerRepository;
import model.Customer;
import service.api.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer entity) {
        return repository.create(entity);
    }

    @Override
    public Optional<Customer> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Customer entity) {
        repository.delete(entity);
    }
}
