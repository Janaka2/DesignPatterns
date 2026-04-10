package org.example.data_consistency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<ID, T> implements Repository<ID, T> {
    private final Map<ID, T> store = new HashMap<>();

    @Override
    public void save(ID id, T entity) {
        store.put(id, entity);
    }

    @Override
    public T findById(ID id) {
        return store.get(id);
    }

    @Override
    public List<T> findBySpecification(Specification<T> specification) {
        return store.values().stream().filter(specification::isSatisfiedBy).toList();
    }
}
