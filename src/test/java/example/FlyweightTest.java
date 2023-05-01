package example;

import org.example.structural.flyweight.Shape;
import org.example.structural.flyweight.ShapeFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FlyweightTest {
    @Test
    public void testFlyweightPattern() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Shape redCircle1 = ShapeFactory.getCircle("red");
        redCircle1.draw(10, 20);

        Shape redCircle2 = ShapeFactory.getCircle("red");
        redCircle2.draw(30, 40);

        Shape blueCircle1 = ShapeFactory.getCircle("blue");
        blueCircle1.draw(50, 60);

        String expectedOutput = "Creating a new red circle." + System.lineSeparator() +
                "Drawing a red circle at (10, 20)" + System.lineSeparator() +
                "Drawing a red circle at (30, 40)" + System.lineSeparator() +
                "Creating a new blue circle." + System.lineSeparator() +
                "Drawing a blue circle at (50, 60)" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}

