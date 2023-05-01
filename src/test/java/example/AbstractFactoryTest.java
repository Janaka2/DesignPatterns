package example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractFactoryTest {
    @Test
    public void testAbstractFactory() {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        assertNotNull(shapeFactory);

        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        assertNotNull(colorFactory);

        Shape circle = shapeFactory.getShape("CIRCLE");
        assertNotNull(circle);
        assertTrue(circle instanceof Circle
