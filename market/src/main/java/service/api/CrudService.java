package service.api;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, E> {

    E save(E entity);

    Optional<E> getById(T id);

    List<E> getAll();

    void delete(E entity);

}
