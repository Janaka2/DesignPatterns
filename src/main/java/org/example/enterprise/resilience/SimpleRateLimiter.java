package org.example.enterprise.resilience;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleRateLimiter {
    private final int maxRequests;
    private final Duration window;
    private final Clock clock;
    private final Deque<Instant> requestTimes = new ArrayDeque<>();

    public SimpleRateLimiter(int maxRequests, Duration window, Clock clock) {
        this.maxRequests = maxRequests;
        this.window = window;
        this.clock = clock;
    }

    public synchronized boolean allowRequest() {
        Instant now = Instant.now(clock);
        while (!requestTimes.isEmpty() && requestTimes.peekFirst().isBefore(now.minus(window))) {
            requestTimes.removeFirst();
        }

        if (requestTimes.size() >= maxRequests) {
            return false;
        }

        requestTimes.addLast(now);
        return true;
    }
}
