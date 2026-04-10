package org.example.creational.factory;

public class ShapeFactory {

    public Shape createShape(ShapeType shapeType) {
        if (shapeType == null) {
            throw new IllegalArgumentException("shapeType cannot be null");
        }

        return switch (shapeType) {
            case CIRCLE -> new Circle();
            case SQUARE -> new Square();
            case RECTANGLE -> new Rectangle();
        };
    }

    /**
     * @deprecated Use {@link #createShape(ShapeType)} with {@link ShapeType#from(String)}.
     *             This method returns {@code null} for invalid input for backward compatibility.
     */
    @Deprecated(since = "1.1", forRemoval = false)
    public Shape getShape(String shapeType) {
        return ShapeType.from(shapeType)
                .map(this::createShape)
                .orElse(null);
    }
}
