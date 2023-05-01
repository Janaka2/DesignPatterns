package example;

import org.example.solid.s.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    @Test
    public void testBook() {
        Book book = new Book("Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides");
        assertEquals("Design Patterns", book.getTitle());
        assertEquals("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", book.getAuthor());
    }
}
