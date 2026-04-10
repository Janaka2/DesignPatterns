package org.example.enterprise.resilience;

import java.util.concurrent.Semaphore;

public class Bulkhead {
    private final Semaphore semaphore;

    public Bulkhead(int maxConcurrentCalls) {
        this.semaphore = new Semaphore(maxConcurrentCalls);
    }

    public <T> T execute(CheckedSupplier<T> supplier) {
        if (!semaphore.tryAcquire()) {
            throw new IllegalStateException("Bulkhead capacity reached");
        }

        try {
            return supplier.get();
        } catch (Exception e) {
            throw new IllegalStateException("Bulkhead operation failed", e);
        } finally {
            semaphore.release();
        }
    }

    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws Exception;
    }
}
