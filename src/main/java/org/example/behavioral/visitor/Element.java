package org.example.behavioral.visitor;

public interface Element {
    String accept(Visitor visitor);
}
