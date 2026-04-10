package org.example.cloudnative.observability;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import org.example.enterprise.resilience.CircuitBreaker;

import java.util.Objects;
import java.util.function.Supplier;

public class ObservedCircuitBreaker {
    private static final AttributeKey<String> OUTCOME = AttributeKey.stringKey("outcome");

    private final CircuitBreaker delegate;
    private final LongCounter executionCounter;

    public ObservedCircuitBreaker(CircuitBreaker delegate, Meter meter) {
        this.delegate = Objects.requireNonNull(delegate);
        this.executionCounter = Objects.requireNonNull(meter)
                .counterBuilder("pattern.circuit_breaker.executions")
                .setDescription("Circuit breaker execution outcomes")
                .build();
    }

    public <T> T execute(Supplier<T> operation) {
        try {
            T result = delegate.execute(operation);
            executionCounter.add(1, Attributes.of(OUTCOME, "success"));
            return result;
        } catch (IllegalStateException openCircuit) {
            executionCounter.add(1, Attributes.of(OUTCOME, "rejected"));
            throw openCircuit;
        } catch (RuntimeException failure) {
            executionCounter.add(1, Attributes.of(OUTCOME, "failure"));
            throw failure;
        }
    }
}
