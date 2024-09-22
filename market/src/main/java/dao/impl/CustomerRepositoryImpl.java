package dao.impl;

import dao.api.CustomerRepository;
import model.Customer;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final DataSource dataSource;

    public CustomerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Customer create(Customer entity) {
        return null;
    }

    @Override
    public Customer update(Customer entity) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public Optional<Customer> getById(UUID id) {
        return null;
    }

    @Override
    public void delete(Customer entity) {

    }
}
