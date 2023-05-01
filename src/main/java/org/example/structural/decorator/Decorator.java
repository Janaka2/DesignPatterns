package org.example.structural.decorator;

public abstract class Decorator implements Component {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public abstract String operation();
}

