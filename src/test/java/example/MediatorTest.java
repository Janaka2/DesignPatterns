package example;

import org.example.behavioral.mediator.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediatorTest {
    @Test
    public void testMediatorPattern() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Mediator mediator = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1(mediator);
        Colleague colleague2 = new ConcreteColleague2(mediator);
        ((ConcreteMediator) mediator).setColleague1(colleague1);
        ((ConcreteMediator) mediator).setColleague2(colleague2);

        colleague2.send("Hello from Colleague2");
        colleague1.send("Hello from Colleague1");

        String expectedOutput = "ConcreteColleague1 received: Hello from Colleague2" + System.lineSeparator()
                + "ConcreteColleague2 received: Hello from Colleague1" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}
