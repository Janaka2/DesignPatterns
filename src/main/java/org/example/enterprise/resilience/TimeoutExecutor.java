package org.example.enterprise.resilience;

import java.time.Duration;
import java.util.concurrent.*;

public class TimeoutExecutor {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public <T> T execute(Callable<T> task, Duration timeout) {
        Future<T> future = executor.submit(task);
        try {
            return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw new IllegalStateException("Operation timed out", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting", e);
        } catch (ExecutionException e) {
            throw new IllegalStateException("Operation failed", e.getCause());
        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
