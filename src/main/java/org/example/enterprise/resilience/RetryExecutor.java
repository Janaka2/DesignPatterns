package org.example.enterprise.resilience;

import java.util.function.Supplier;

public class RetryExecutor {
    private final int maxAttempts;
    private final BackoffStrategy backoffStrategy;

    public RetryExecutor(int maxAttempts, BackoffStrategy backoffStrategy) {
        this.maxAttempts = maxAttempts;
        this.backoffStrategy = backoffStrategy;
    }

    public <T> T execute(Supplier<T> operation) {
        RuntimeException lastError = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return operation.get();
            } catch (RuntimeException ex) {
                lastError = ex;
                if (attempt < maxAttempts) {
                    sleep(backoffStrategy.nextDelayMillis(attempt));
                }
            }
        }

        throw new IllegalStateException("Retry attempts exhausted", lastError);
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(Math.max(0, millis));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted during backoff", e);
        }
    }
}
