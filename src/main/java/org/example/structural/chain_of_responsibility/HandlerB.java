package org.example.structural.chain_of_responsibility;

public class HandlerB extends Handler {
    @Override
    public String handleRequest(int value) {
        if (value >= 10 && value < 20) {
            return "Handler B";
        } else if (successor != null) {
            return successor.handleRequest(value);
        }
        return null;
    }
}
