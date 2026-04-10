package example;

import org.example.behavioral.state.ConcreteStateA;
import org.example.behavioral.state.ConcreteStateB;
import org.example.behavioral.state.Context;
import org.example.behavioral.state.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class StateTest {
    @Test
    public void testStatePattern() {
        State initialState = ConcreteStateA.INSTANCE;
        Context context = new Context(initialState);

        context.request();
        assertSame(ConcreteStateB.INSTANCE, context.getState());

        context.request();
        assertSame(ConcreteStateA.INSTANCE, context.getState());
    }
}
