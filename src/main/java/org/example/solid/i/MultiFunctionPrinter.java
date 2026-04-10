package org.example.solid.i;

import java.util.Objects;

public final class MultiFunctionPrinter {
    private final Printer printer;
    private final Scanner scanner;
    private final Fax fax;

    public MultiFunctionPrinter(Printer printer, Scanner scanner, Fax fax) {
        this.printer = Objects.requireNonNull(printer, "printer must not be null");
        this.scanner = Objects.requireNonNull(scanner, "scanner must not be null");
        this.fax = Objects.requireNonNull(fax, "fax must not be null");
    }

    public void print(Document document) {
        printer.print(Objects.requireNonNull(document, "document must not be null"));
    }

    public void scan(Document document) {
        scanner.scan(Objects.requireNonNull(document, "document must not be null"));
    }

    public void fax(Document document) {
        fax.fax(Objects.requireNonNull(document, "document must not be null"));
    }
}
