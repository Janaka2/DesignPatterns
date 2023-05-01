package org.example.structural.chain_of_responsibility;

public class HandlerC extends Handler {
    @Override
    public String handleRequest(int value) {
        if (value >= 20) {
            return "Handler C";
        } else if (successor != null) {
            return successor.handleRequest(value);
        }
        return null;
    }
}
