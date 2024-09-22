package service.impl;

import dao.api.ProductRepository;
import model.Product;
import service.api.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final EventManager eventManager;

    public ProductServiceImpl(ProductRepository repository,
                              EventManager eventManager) {
        this.repository = repository;
        this.eventManager = eventManager;
    }

    @Override
    public Product save(Product product) {
        repository.create(product);
        if(product.sale()) eventManager.publish("NEW_PRODUCT_ON_SALE", product);
        return product;
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
