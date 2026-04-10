package example;

import org.example.enterprise.resilience.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ResiliencePatternsTest {

    private final TimeoutExecutor timeoutExecutor = new TimeoutExecutor();

    @AfterEach
    public void cleanup() {
        timeoutExecutor.shutdown();
    }

    @Test
    public void retryAndBackoffAndIdempotency() {
        RetryExecutor retryExecutor = new RetryExecutor(3, new ExponentialBackoffStrategy(0, 1));
        AtomicInteger attempts = new AtomicInteger();

        String value = retryExecutor.execute(() -> {
            if (attempts.incrementAndGet() < 3) {
                throw new IllegalArgumentException("Transient failure");
            }
            return "ok";
        });

        IdempotencyKeyStore keyStore = new IdempotencyKeyStore();
        AtomicInteger runs = new AtomicInteger();
        String first = keyStore.execute("k-1", () -> "result-" + runs.incrementAndGet());
        String second = keyStore.execute("k-1", () -> "result-" + runs.incrementAndGet());

        assertEquals("ok", value);
        assertEquals(3, attempts.get());
        assertEquals(first, second);
        assertEquals(1, runs.get());
    }

    @Test
    public void circuitBreakerBulkheadTimeoutRateLimiterAndDlq() {
        CircuitBreaker breaker = new CircuitBreaker(2, Duration.ofSeconds(30),
                Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC));

        assertThrows(RuntimeException.class, () -> breaker.execute(() -> { throw new RuntimeException("down"); }));
        assertThrows(RuntimeException.class, () -> breaker.execute(() -> { throw new RuntimeException("down"); }));
        assertThrows(IllegalStateException.class, () -> breaker.execute(() -> "never called"));

        Bulkhead bulkhead = new Bulkhead(1);
        String bulkheadResult = bulkhead.execute(() -> "isolated");

        String timeoutResult = timeoutExecutor.execute(() -> "fast", Duration.ofMillis(100));

        SimpleRateLimiter limiter = new SimpleRateLimiter(2, Duration.ofMinutes(1),
                Clock.fixed(Instant.parse("2026-01-01T00:00:00Z"), ZoneOffset.UTC));
        assertTrue(limiter.allowRequest());
        assertTrue(limiter.allowRequest());
        assertFalse(limiter.allowRequest());

        DeadLetterQueue deadLetterQueue = new DeadLetterQueue();
        deadLetterQueue.publish("event-1");

        assertEquals("isolated", bulkheadResult);
        assertEquals("fast", timeoutResult);
        assertEquals(1, deadLetterQueue.messages().size());
    }
}
