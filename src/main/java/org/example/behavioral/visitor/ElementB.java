package org.example.behavioral.visitor;

public class ElementB implements Element {
    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
