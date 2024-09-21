package service.impl;

import dao.api.ProductRepository;
import model.Product;
import service.api.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product entity) {
        return repository.create(entity);
    }

    @Override
    public Optional<Product> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(Product entity) {
        repository.delete(entity);
    }
}
