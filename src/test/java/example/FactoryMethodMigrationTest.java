package example;

import org.example.creational.factory.Circle;
import org.example.creational.factory.Shape;
import org.example.creational.factory.ShapeFactory;
import org.example.creational.factory.ShapeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FactoryMethodMigrationTest {

    @Test
    void shouldUseTypeSafeFactoryMethodForNewCode() {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape = shapeFactory.createShape(ShapeType.CIRCLE);

        assertNotNull(shape);
        assertInstanceOf(Circle.class, shape);
    }

    @Test
    void shouldKeepLegacyMethodCompatibleDuringMigration() {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape = shapeFactory.getShape("not-supported");

        assertNull(shape);
    }

    @Test
    void shouldRejectNullForNewApi() {
        ShapeFactory shapeFactory = new ShapeFactory();

        assertThrows(IllegalArgumentException.class, () -> shapeFactory.createShape(null));
    }
}
