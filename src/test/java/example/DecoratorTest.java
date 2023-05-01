package example;

import org.example.structural.decorator.Component;
import org.example.structural.decorator.ConcreteComponent;
import org.example.structural.decorator.ConcreteDecoratorA;
import org.example.structural.decorator.ConcreteDecoratorB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTest {
    @Test
    public void testDecoratorPattern() {
        Component component = new ConcreteComponent();
        Component decoratorA = new ConcreteDecoratorA(component);
        Component decoratorB = new ConcreteDecoratorB(decoratorA);

        assertEquals("ConcreteComponent", component.operation());
        assertEquals("ConcreteDecoratorA(ConcreteComponent)", decoratorA.operation());
        assertEquals("ConcreteDecoratorB(ConcreteDecoratorA(ConcreteComponent))", decoratorB.operation());
    }
}

