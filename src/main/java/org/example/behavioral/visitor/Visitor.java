package org.example.behavioral.visitor;

public interface Visitor {
    String visit(ElementA elementA);

    String visit(ElementB elementB);
}

