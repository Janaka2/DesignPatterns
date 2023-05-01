package example;

import org.example.solid.i.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class MultiFunctionPrinterTest {
    @Test
    public void testMultiFunctionPrinter() {
        Document document = new Document("Test document content");

        Printer printer = mock(Printer.class);
        Scanner scanner = mock(Scanner.class);
        Fax fax = mock(Fax.class);

        MultiFunctionPrinter multiFunctionPrinter = new MultiFunctionPrinter(printer, scanner, fax);
        multiFunctionPrinter.print(document);
        multiFunctionPrinter.scan(document);
        multiFunctionPrinter.fax(document);

        verify(printer).print(document);
        verify(scanner).scan(document);
        verify(fax).fax(document);
    }
}
