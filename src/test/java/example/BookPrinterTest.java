package example;

import org.example.solid.s.Book;
import org.example.solid.s.BookPrinter;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookPrinterTest {
    @Test
    public void testBookPrinter() {
        Book book = new Book("Design Patterns", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides");
        BookPrinter bookPrinter = new BookPrinter();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        bookPrinter.printBookDetails(book);

        String expectedOutput = "Title: Design Patterns" + System.lineSeparator()
                + "Author: Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides" + System.lineSeparator();

        assertEquals(expectedOutput, outputStream.toString());
    }
}
