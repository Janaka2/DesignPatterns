package org.example.creational.abstractfactory;

import java.util.Locale;

public interface AbstractFactory {
    Shape getShape(String shapeType);
    Color getColor(String colorType);
}

class ShapeFactory implements AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }

        return switch (shapeType.toUpperCase(Locale.ROOT)) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            case "SQUARE" -> new Square();
            default -> null;
        };
    }

    @Override
    public Color getColor(String colorType) {
        return null;
    }
}

class ColorFactory implements AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }

    @Override
    public Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }

        return switch (colorType.toUpperCase(Locale.ROOT)) {
            case "RED" -> new Red();
            case "GREEN" -> new Green();
            case "BLUE" -> new Blue();
            default -> null;
        };
    }
}

