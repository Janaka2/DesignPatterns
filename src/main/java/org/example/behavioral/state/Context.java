package org.example.behavioral.state;

import java.util.Objects;

public final class Context {
    private State state;

    public Context(State state) {
        this.state = Objects.requireNonNull(state, "state must not be null");
    }

    public void setState(State state) {
        this.state = Objects.requireNonNull(state, "state must not be null");
    }

    public State getState() {
        return state;
    }

    public void request() {
        state.handle(this);
    }
}
