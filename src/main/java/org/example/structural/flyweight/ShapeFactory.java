package org.example.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private static final Map<String, Shape> shapes = new HashMap<>();

    public static Shape getCircle(String color) {
        Shape shape = shapes.get(color);
        if (shape == null) {
            shape = new Circle(color);
            shapes.put(color, shape);
            System.out.println("Creating a new " + color + " circle.");
        }
        return shape;
    }
}
