package dao.api;

import java.util.List;

public interface CrudRepository<T, E> {
    E save(E entity);

    List<E> getAll();

    E getById(T id);

    void delete(E entity);

}
