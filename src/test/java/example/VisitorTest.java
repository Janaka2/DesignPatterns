package example;

import org.example.behavioral.visitor.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitorTest {
    @Test
    public void testVisitorPattern() {
        Visitor visitor = new ConcreteVisitor();
        Element elementA = new ElementA();
        Element elementB = new ElementB();

        assertEquals("Visited ElementA", elementA.accept(visitor));
        assertEquals("Visited ElementB", elementB.accept(visitor));
    }
}