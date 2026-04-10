package org.example.enterprise.resilience;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class CircuitBreaker {
    private enum State { CLOSED, OPEN, HALF_OPEN }

    private final int failureThreshold;
    private final Duration openWindow;
    private final Clock clock;
    private State state = State.CLOSED;
    private int consecutiveFailures = 0;
    private Instant openedAt = Instant.EPOCH;

    public CircuitBreaker(int failureThreshold, Duration openWindow, Clock clock) {
        this.failureThreshold = failureThreshold;
        this.openWindow = openWindow;
        this.clock = clock;
    }

    public synchronized <T> T execute(Supplier<T> operation) {
        if (state == State.OPEN && Instant.now(clock).isBefore(openedAt.plus(openWindow))) {
            throw new IllegalStateException("Circuit breaker is OPEN");
        }

        if (state == State.OPEN) {
            state = State.HALF_OPEN;
        }

        try {
            T result = operation.get();
            reset();
            return result;
        } catch (RuntimeException ex) {
            recordFailure();
            throw ex;
        }
    }

    private void reset() {
        consecutiveFailures = 0;
        state = State.CLOSED;
    }

    private void recordFailure() {
        consecutiveFailures++;
        if (consecutiveFailures >= failureThreshold || state == State.HALF_OPEN) {
            state = State.OPEN;
            openedAt = Instant.now(clock);
        }
    }
}
