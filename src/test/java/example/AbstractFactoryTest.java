package example;


import org.example.creational.abstractfactory.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractFactoryTest {

    @Test
    public void testAbstractFactory() {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        assertNotNull(shapeFactory);

        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        assertNotNull(colorFactory);

        Shape circle = shapeFactory.getShape("CIRCLE");
        assertNotNull(circle);
        assertTrue(circle instanceof Circle);

        Shape rectangle = shapeFactory.getShape("RECTANGLE");
        assertNotNull(rectangle);
        assertTrue(rectangle instanceof Rectangle);

        Shape square = shapeFactory.getShape("SQUARE");
        assertNotNull(square);
        assertTrue(square instanceof Square);

        Color red = colorFactory.getColor("RED");
        assertNotNull(red);
        assertTrue(red instanceof Red);

        Color green = colorFactory.getColor("GREEN");
        assertNotNull(green);
        assertTrue(green instanceof Green);

        Color blue = colorFactory.getColor("BLUE");
        assertNotNull(blue);
        assertTrue(blue instanceof Blue);
    }
}