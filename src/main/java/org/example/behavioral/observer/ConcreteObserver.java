package org.example.behavioral.observer;

import java.util.Objects;

public final class ConcreteObserver implements Observer {
    private final String name;
    private String observerState;

    public ConcreteObserver(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    @Override
    public void update(String message) {
        observerState = Objects.requireNonNull(message, "message must not be null");
    }

    public String getObserverState() {
        return observerState;
    }

    public String getName() {
        return name;
    }
}
