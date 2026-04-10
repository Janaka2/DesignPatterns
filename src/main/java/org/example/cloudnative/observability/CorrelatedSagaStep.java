package org.example.cloudnative.observability;

import java.util.function.Consumer;

public record CorrelatedSagaStep(String name, Consumer<String> action, Consumer<String> compensation) {
}
