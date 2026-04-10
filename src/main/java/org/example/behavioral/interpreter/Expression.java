package org.example.behavioral.interpreter;

public sealed interface Expression permits Number, Plus, Minus {
    int interpret();
}
