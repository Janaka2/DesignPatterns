package example;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.data.MetricData;
import io.opentelemetry.sdk.metrics.export.InMemoryMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.testing.exporter.InMemorySpanExporter;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.example.cloudnative.observability.CorrelatedSagaOrchestrator;
import org.example.cloudnative.observability.CorrelatedSagaStep;
import org.example.cloudnative.observability.ObservedCircuitBreaker;
import org.example.cloudnative.observability.ObservedCommandExecutor;
import org.example.enterprise.resilience.CircuitBreaker;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObservabilityPatternsTest {

    @Test
    public void commandLatencyAndSagaCorrelationProduceSpans() {
        InMemorySpanExporter spanExporter = InMemorySpanExporter.create();
        InMemoryMetricReader metricReader = InMemoryMetricReader.create();

        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(SimpleSpanProcessor.create(spanExporter))
                .setResource(Resource.empty())
                .build();
        SdkMeterProvider meterProvider = SdkMeterProvider.builder()
                .registerMetricReader(metricReader)
                .setResource(Resource.empty())
                .build();

        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .setMeterProvider(meterProvider)
                .build();

        Tracer tracer = openTelemetry.getTracer("patterns-test");
        Meter meter = openTelemetry.getMeter("patterns-test");

        ObservedCommandExecutor commandExecutor = new ObservedCommandExecutor(tracer, meter);
        String result = commandExecutor.execute("CreateOrder", () -> "ok");

        AtomicReference<String> seenCorrelation = new AtomicReference<>();
        CorrelatedSagaOrchestrator saga = new CorrelatedSagaOrchestrator(tracer);
        saga.execute("corr-42", List.of(
                new CorrelatedSagaStep("reserveInventory", seenCorrelation::set, value -> {
                })
        ));

        List<SpanData> spans = spanExporter.getFinishedSpanItems();
        List<String> spanNames = spans.stream().map(SpanData::getName).collect(Collectors.toList());
        assertTrue(spanNames.contains("command.execute"));
        assertTrue(spanNames.contains("saga.orchestration"));
        assertEquals("corr-42", seenCorrelation.get());
        assertEquals("ok", result);

        List<MetricData> metrics = metricReader.collectAllMetrics();
        assertTrue(metrics.stream().anyMatch(m -> m.getName().equals("pattern.command.latency.ms")));

        tracerProvider.close();
        meterProvider.close();
    }

    @Test
    public void circuitBreakerMetricsRecordOutcomes() {
        InMemoryMetricReader metricReader = InMemoryMetricReader.create();
        SdkMeterProvider meterProvider = SdkMeterProvider.builder()
                .registerMetricReader(metricReader)
                .build();
        Meter meter = OpenTelemetrySdk.builder()
                .setMeterProvider(meterProvider)
                .build()
                .getMeter("patterns-test");

        CircuitBreaker breaker = new CircuitBreaker(1, Duration.ofMinutes(1),
                Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC));
        ObservedCircuitBreaker observed = new ObservedCircuitBreaker(breaker, meter);

        assertThrows(RuntimeException.class, () -> observed.execute(() -> {
            throw new RuntimeException("down");
        }));
        assertThrows(IllegalStateException.class, () -> observed.execute(() -> "never"));

        List<MetricData> metrics = metricReader.collectAllMetrics();
        MetricData counter = metrics.stream()
                .filter(metric -> metric.getName().equals("pattern.circuit_breaker.executions"))
                .findFirst()
                .orElseThrow();

        int points = counter.getLongSumData().getPoints().size();
        assertEquals(2, points);

        meterProvider.close();
    }
}
