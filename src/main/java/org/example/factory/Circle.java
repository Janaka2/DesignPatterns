package org.example.factory;

import org.example.factory.Shape;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }
}
