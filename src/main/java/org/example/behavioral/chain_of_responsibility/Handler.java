package org.example.behavioral.chain_of_responsibility;

public abstract class Handler {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    public abstract String handleRequest(int value);
}

