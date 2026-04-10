package org.example.behavioral.state;

public final class ConcreteStateA implements State {
    public static final ConcreteStateA INSTANCE = new ConcreteStateA();

    private ConcreteStateA() {
    }

    @Override
    public void handle(Context context) {
        context.setState(ConcreteStateB.INSTANCE);
    }
}
