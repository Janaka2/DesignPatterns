package org.example.cloudnative.observability;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CorrelatedSagaOrchestrator {
    private final Tracer tracer;

    public CorrelatedSagaOrchestrator(Tracer tracer) {
        this.tracer = Objects.requireNonNull(tracer);
    }

    public void execute(String correlationId, List<CorrelatedSagaStep> steps) {
        Span sagaSpan = tracer.spanBuilder("saga.orchestration")
                .setSpanKind(SpanKind.INTERNAL)
                .setAttribute("pattern.type", "saga")
                .setAttribute("saga.correlation_id", correlationId)
                .startSpan();

        List<CorrelatedSagaStep> completed = new ArrayList<>();
        try {
            for (CorrelatedSagaStep step : steps) {
                runStep(correlationId, step);
                completed.add(step);
            }
            sagaSpan.setStatus(StatusCode.OK);
        } catch (RuntimeException ex) {
            rollback(correlationId, completed);
            sagaSpan.recordException(ex);
            sagaSpan.setStatus(StatusCode.ERROR, "saga failed");
            throw new IllegalStateException("Correlated saga orchestration failed", ex);
        } finally {
            sagaSpan.end();
        }
    }

    private void runStep(String correlationId, CorrelatedSagaStep step) {
        Span span = tracer.spanBuilder("saga.step")
                .setAttribute("saga.correlation_id", correlationId)
                .setAttribute("saga.step", step.name())
                .startSpan();
        try {
            step.action().accept(correlationId);
            span.setStatus(StatusCode.OK);
        } finally {
            span.end();
        }
    }

    private void rollback(String correlationId, List<CorrelatedSagaStep> completed) {
        for (int i = completed.size() - 1; i >= 0; i--) {
            CorrelatedSagaStep step = completed.get(i);
            Span compensationSpan = tracer.spanBuilder("saga.compensation")
                    .setAttribute("saga.correlation_id", correlationId)
                    .setAttribute("saga.step", step.name())
                    .startSpan();
            try {
                step.compensation().accept(correlationId);
                compensationSpan.setStatus(StatusCode.OK);
            } finally {
                compensationSpan.end();
            }
        }
    }
}
