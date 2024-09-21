package dao.api;

import model.User;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UUID, User> {
}
