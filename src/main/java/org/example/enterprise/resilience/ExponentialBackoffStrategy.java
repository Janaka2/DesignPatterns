package org.example.enterprise.resilience;

public class ExponentialBackoffStrategy implements BackoffStrategy {
    private final long initialDelayMillis;
    private final long maxDelayMillis;

    public ExponentialBackoffStrategy(long initialDelayMillis, long maxDelayMillis) {
        this.initialDelayMillis = initialDelayMillis;
        this.maxDelayMillis = maxDelayMillis;
    }

    @Override
    public long nextDelayMillis(int attempt) {
        long delay = initialDelayMillis * (1L << Math.max(0, attempt - 1));
        return Math.min(delay, maxDelayMillis);
    }
}
