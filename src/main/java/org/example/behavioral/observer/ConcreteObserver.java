package org.example.behavioral.observer;

public class ConcreteObserver implements Observer {
    private String observerState;

    @Override
    public void update(String message) {
        observerState = message;
        System.out.println("Observer state updated: " + observerState);
    }

    public String getObserverState() {
        return observerState;
    }
}