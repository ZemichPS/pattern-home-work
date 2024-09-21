package dao.impl;

import dao.api.UserRepository;
import model.User;

import java.util.List;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User getById(UUID id) {
        return null;
    }

    @Override
    public void delete(User entity) {

    }
}
