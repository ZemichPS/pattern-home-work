package dao.api;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, E> {
    E create(E entity);
    E update(E entity);

    List<E> getAll();

    Optional<E> getById(T id);

    void delete(E entity);

}
