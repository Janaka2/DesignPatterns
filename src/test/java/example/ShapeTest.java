package example;

import org.example.solid.l.Rectangle;
import org.example.solid.l.Shape;
import org.example.solid.l.Square;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShapeTest {
    @Test
    public void testArea() {
        Shape rectangle = new Rectangle(4, 5);
        Shape square = new Square(3);

        assertEquals(20, rectangle.area(), 0.001);
        assertEquals(9, square.area(), 0.001);
    }
}
