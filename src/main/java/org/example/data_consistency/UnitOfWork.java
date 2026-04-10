package org.example.data_consistency;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final List<Runnable> operations = new ArrayList<>();

    public void register(Runnable operation) {
        operations.add(operation);
    }

    public void commit() {
        operations.forEach(Runnable::run);
        operations.clear();
    }

    public int pendingOperations() {
        return operations.size();
    }
}
