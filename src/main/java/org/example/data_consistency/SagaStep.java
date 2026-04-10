package org.example.data_consistency;

public record SagaStep(Runnable action, Runnable compensation) {
}
