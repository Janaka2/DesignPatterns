package org.example.solid.s;

import java.io.PrintStream;
import java.util.Objects;

public final class BookPrinter {
    private final PrintStream output;

    public BookPrinter() {
        this(System.out);
    }

    public BookPrinter(PrintStream output) {
        this.output = Objects.requireNonNull(output, "output must not be null");
    }

    public void printBookDetails(Book book) {
        Objects.requireNonNull(book, "book must not be null");
        output.println("Title: " + book.getTitle());
        output.println("Author: " + book.getAuthor());
    }
}
