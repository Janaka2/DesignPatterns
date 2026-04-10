package org.example.creational.abstractfactory;

import java.util.Locale;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice == null) {
            return null;
        }

        return switch (choice.toUpperCase(Locale.ROOT)) {
            case "SHAPE" -> new ShapeFactory();
            case "COLOR" -> new ColorFactory();
            default -> null;
        };
    }
}
