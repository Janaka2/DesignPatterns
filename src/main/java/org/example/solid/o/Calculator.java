package org.example.solid.o;
public class Calculator {
    public double calculate(Operation operation, double a, double b) {
        return operation.perform(a, b);
    }
}
