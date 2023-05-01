package example;

import org.example.factory.Circle;
import org.example.factory.Square;
import org.example.factory.Rectangle;
import org.example.factory.Shape;
import org.example.factory.ShapeFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactoryMethodTest {
    @Test
    public void testFactoryMethod() {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape1 = shapeFactory.getShape("CIRCLE");
        assertNotNull(shape1);
        assertTrue(shape1 instanceof Circle);
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        assertNotNull(shape2);
        assertTrue(shape2 instanceof Rectangle);
        shape2.draw();

        Shape shape3 = shapeFactory.getShape("SQUARE");
        assertNotNull(shape3);
        assertTrue(shape3 instanceof Square);
        shape3.draw();
    }
}
