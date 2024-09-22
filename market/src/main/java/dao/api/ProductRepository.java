package dao.api;

import model.Product;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<UUID, Product> {
}
