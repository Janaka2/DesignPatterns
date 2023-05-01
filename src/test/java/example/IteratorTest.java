package example;


import org.example.behavioral.iterator.ConcreteIterator;
import org.example.behavioral.iterator.Iterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorTest {
    @Test
    public void testIteratorPattern() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Iterator<Integer> iterator = new ConcreteIterator<>(numbers);

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next());

        assertFalse(iterator.hasNext());
    }
}
