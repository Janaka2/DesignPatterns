package example;

import org.example.structural.chain_of_responsibility.Handler;
import org.example.structural.chain_of_responsibility.HandlerA;
import org.example.structural.chain_of_responsibility.HandlerB;
import org.example.structural.chain_of_responsibility.HandlerC;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChainOfResponsibilityTest {
    @Test
    public void testChainOfResponsibilityPattern() {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        assertEquals("Handler A", handlerA.handleRequest(5));
        assertEquals("Handler B", handlerA.handleRequest(15));
        assertEquals("Handler C", handlerA.handleRequest(25));
        assertNull(handlerA.handleRequest(-5));
    }
}
