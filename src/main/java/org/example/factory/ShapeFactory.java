package org.example;

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a square");
    }
}

public class ShapeFactory {
    public Shape getShape(String shapeType) {
        if ("CIRCLE".equalsIgnoreCase(shapeType)) {
            return new Circle();
        } else if ("SQUARE".equalsIgnoreCase(shapeType)) {
            return new Square();
        }
        return null;
    }
}