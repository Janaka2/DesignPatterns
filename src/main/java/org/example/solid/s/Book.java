package org.example.solid.s;

import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = requireNotBlank(title, "title");
        this.author = requireNotBlank(author, "author");
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    private static String requireNotBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }
}
