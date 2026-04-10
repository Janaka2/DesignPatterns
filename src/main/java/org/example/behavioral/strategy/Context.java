package org.example.behavioral.strategy;

import java.util.Objects;

public final class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "strategy must not be null");
    }

    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}
