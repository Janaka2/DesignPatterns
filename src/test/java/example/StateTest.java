package example;


import org.example.behavioral.state.ConcreteStateA;
import org.example.behavioral.state.ConcreteStateB;
import org.example.behavioral.state.Context;
import org.example.behavioral.state.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StateTest {
    @Test
    public void testStatePattern() {
        State initialState = new ConcreteStateA();
        Context context = new Context(initialState);

        context.request();
        assertTrue(context.getState() instanceof ConcreteStateB);

        context.request();
        assertTrue(context.getState() instanceof ConcreteStateA);
    }
}
