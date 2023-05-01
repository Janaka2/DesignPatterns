package example;

import org.example.structural.composite.Circle;
import org.example.structural.composite.Graphic;
import org.example.structural.composite.GraphicComposite;
import org.example.structural.composite.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CompositeTest {
    @Test
    public void testCompositePattern() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Graphic circle1 = new Circle();
        Graphic circle2 = new Circle();
        Graphic rectangle = new Rectangle();

        GraphicComposite composite1 = new GraphicComposite();
        composite1.addGraphic(circle1);
        composite1.addGraphic(circle2);

        GraphicComposite composite2 = new GraphicComposite();
        composite2.addGraphic(rectangle);

        GraphicComposite mainComposite = new GraphicComposite();
        mainComposite.addGraphic(composite1);
        mainComposite.addGraphic(composite2);

        mainComposite.draw();

        String expectedOutput = "Drawing a circle." + System.lineSeparator() +
                "Drawing a circle." + System.lineSeparator() +
                "Drawing a rectangle." + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}
