package example;

import org.example.behavioral.strategy.AddStrategy;
import org.example.behavioral.strategy.Context;
import org.example.behavioral.strategy.MultiplyStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyTest {
    @Test
    public void testStrategyPattern() {
        Context context = new Context(new AddStrategy());
        assertEquals(5, context.executeStrategy(2, 3));

        context.setStrategy(new MultiplyStrategy());
        assertEquals(6, context.executeStrategy(2, 3));
    }
}
