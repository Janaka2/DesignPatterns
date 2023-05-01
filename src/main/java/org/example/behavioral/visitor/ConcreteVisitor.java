package org.example.behavioral.visitor;

public class ConcreteVisitor implements Visitor {
    @Override
    public String visit(ElementA elementA) {
        return "Visited ElementA";
    }

    @Override
    public String visit(ElementB elementB) {
        return "Visited ElementB";
    }
}
