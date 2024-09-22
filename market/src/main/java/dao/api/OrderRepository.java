package dao.api;

import model.Order;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<UUID, Order> {
}
