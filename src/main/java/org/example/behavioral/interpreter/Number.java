package org.example.behavioral.interpreter;

public record Number(int number) implements Expression {
    @Override
    public int interpret() {
        return number;
    }
}
