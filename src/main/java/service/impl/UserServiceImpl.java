package service.impl;

import dao.api.UserRepository;
import model.User;
import service.api.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User entity) {
        return repository.create(entity);
    }

    @Override
    public Optional<User> getById(UUID id) {
        return repository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);
    }
}
