package org.example.behavioral.visitor;

public class ElementA implements Element {
    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
