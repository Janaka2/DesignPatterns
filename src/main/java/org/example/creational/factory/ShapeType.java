package org.example.creational.factory;

import java.util.Locale;
import java.util.Optional;

public enum ShapeType {
    CIRCLE,
    SQUARE,
    RECTANGLE;

    public static Optional<ShapeType> from(String value) {
        if (value == null || value.isBlank()) {
            return Optional.empty();
        }

        try {
            return Optional.of(ShapeType.valueOf(value.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException ex) {
            return Optional.empty();
        }
    }
}
