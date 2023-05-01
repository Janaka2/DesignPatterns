package org.example.solid.i;

import org.example.solid.i.Document;
import org.example.solid.i.Fax;
import org.example.solid.i.Printer;
import org.example.solid.i.Scanner;

public class MultiFunctionPrinter {
    private Printer printer;
    private Scanner scanner;
    private Fax fax;

    public MultiFunctionPrinter(Printer printer, Scanner scanner, Fax fax) {
        this.printer = printer;
        this.scanner = scanner;
        this.fax = fax;
    }

    public void print(Document document) {
        printer.print(document);
    }

    public void scan(Document document) {
        scanner.scan(document);
    }

    public void fax(Document document) {
        fax.fax(document);
    }
}
