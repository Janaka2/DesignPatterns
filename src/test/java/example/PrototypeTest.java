package example;

import org.example.creational.prototype.Shape;
import org.example.creational.prototype.ShapeCache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrototypeTest {
    @Test
    public void testPrototype() {
        ShapeCache.loadCache();

        Shape clonedShape1 = ShapeCache.getShape("1");
        assertNotNull(clonedShape1);
        assertEquals("Circle", clonedShape1.getType());

        Shape clonedShape2 = ShapeCache.getShape("2");
        assertNotNull(clonedShape2);
        assertEquals("Rectangle", clonedShape2.getType());
    }
}