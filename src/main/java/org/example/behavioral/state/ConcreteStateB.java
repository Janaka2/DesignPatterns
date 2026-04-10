package org.example.behavioral.state;

public final class ConcreteStateB implements State {
    public static final ConcreteStateB INSTANCE = new ConcreteStateB();

    private ConcreteStateB() {
    }

    @Override
    public void handle(Context context) {
        context.setState(ConcreteStateA.INSTANCE);
    }
}
