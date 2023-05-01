package org.example.behavioral.mediator;

public abstract class Colleague {
    private final Mediator mediator;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public void send(String message) {
        mediator.send(message, this);
    }

    public abstract void receive(String message);
}
