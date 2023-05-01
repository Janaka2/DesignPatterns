package org.example.structural.chain_of_responsibility;

public class HandlerA extends Handler {
    @Override
    public String handleRequest(int value) {
        if (value>0 && value < 10) {
            return "Handler A";
        } else if (successor != null) {
            return successor.handleRequest(value);
        }
        return null;
    }
}
