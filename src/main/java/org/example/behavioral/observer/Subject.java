package org.example.behavioral.observer;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Subject {
    private final List<Observer> observers = new CopyOnWriteArrayList<>();

    public void addObserver(Observer observer) {
        Observer validated = Objects.requireNonNull(observer, "observer must not be null");
        if (!observers.contains(validated)) {
            observers.add(validated);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(Objects.requireNonNull(observer, "observer must not be null"));
    }

    public int observerCount() {
        return observers.size();
    }

    public void notifyObservers(String message) {
        String validatedMessage = Objects.requireNonNull(message, "message must not be null");
        for (Observer observer : observers) {
            observer.update(validatedMessage);
        }
    }
}
