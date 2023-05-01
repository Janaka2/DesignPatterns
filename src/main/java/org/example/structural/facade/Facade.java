package org.example.structural.facade;

public class Facade {
    private SubsystemA subsystemA;
    private SubsystemB subsystemB;
    private SubsystemC subsystemC;

    public Facade() {
        subsystemA = new SubsystemA();
        subsystemB = new SubsystemB();
        subsystemC = new SubsystemC();
    }

    public String performComplexOperation() {
        String resultA = subsystemA.operationA();
        String resultB = subsystemB.operationB();
        String resultC = subsystemC.operationC();
        return resultA + " -> " + resultB + " -> " + resultC;
    }
}
