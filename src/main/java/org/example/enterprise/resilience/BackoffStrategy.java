package org.example.enterprise.resilience;

public interface BackoffStrategy {
    long nextDelayMillis(int attempt);
}
