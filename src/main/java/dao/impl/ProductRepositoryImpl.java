package dao.impl;

import dao.api.ProductRepository;
import model.Product;

import java.util.List;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return List.of();
    }

    @Override
    public Product getById(UUID id) {
        return null;
    }

    @Override
    public void delete(Product entity) {

    }
}
