package org.example.cloudnative.observability;

import io.opentelemetry.api.metrics.DoubleHistogram;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ObservedCommandExecutor {
    private final Tracer tracer;
    private final DoubleHistogram latencyHistogram;

    public ObservedCommandExecutor(Tracer tracer, Meter meter) {
        this.tracer = Objects.requireNonNull(tracer);
        this.latencyHistogram = Objects.requireNonNull(meter)
                .histogramBuilder("pattern.command.latency.ms")
                .setDescription("Command execution latency")
                .setUnit("ms")
                .build();
    }

    public <T> T execute(String commandName, Supplier<T> commandAction) {
        long startNanos = System.nanoTime();
        Span span = tracer.spanBuilder("command.execute")
                .setSpanKind(SpanKind.INTERNAL)
                .setAttribute("pattern.type", "command")
                .setAttribute("command.name", commandName)
                .startSpan();

        try {
            T result = commandAction.get();
            span.setStatus(StatusCode.OK);
            return result;
        } catch (RuntimeException ex) {
            span.recordException(ex);
            span.setStatus(StatusCode.ERROR, "command failure");
            throw ex;
        } finally {
            double latencyMillis = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - startNanos) / 1_000.0;
            latencyHistogram.record(latencyMillis);
            span.end();
        }
    }
}
