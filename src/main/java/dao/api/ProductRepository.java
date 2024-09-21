package dao.api;

import model.Product;

import java.rmi.server.UID;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<UUID, Product> {
}
