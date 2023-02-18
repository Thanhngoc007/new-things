package fa.training.studentmanagement.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    List<T> getAll();

    T create(T entity);

    T update(T entity);

    Optional<T> findOne(ID id);

    void delete(T entity);
}
