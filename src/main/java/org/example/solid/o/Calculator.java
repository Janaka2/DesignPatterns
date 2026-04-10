package org.example.solid.o;

import java.util.Objects;

public final class Calculator {
    public double calculate(Operation operation, double a, double b) {
        Objects.requireNonNull(operation, "operation must not be null");
        return operation.perform(a, b);
    }
}
