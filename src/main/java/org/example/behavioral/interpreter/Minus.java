package org.example.behavioral.interpreter;

public record Minus(Expression left, Expression right) implements Expression {
    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}
