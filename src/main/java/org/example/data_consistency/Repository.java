package org.example.data_consistency;

import java.util.List;

public interface Repository<ID, T> {
    void save(ID id, T entity);
    T findById(ID id);
    List<T> findBySpecification(Specification<T> specification);
}
