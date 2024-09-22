package dao.api;

import model.Customer;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<UUID, Customer> {
}
