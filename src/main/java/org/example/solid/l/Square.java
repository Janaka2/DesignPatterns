package org.example.solid.l;

public final class Square implements Shape {
    private final double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("side must be greater than zero");
        }
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double area() {
        return side * side;
    }
}
