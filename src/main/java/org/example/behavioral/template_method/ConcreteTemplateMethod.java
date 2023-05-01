package org.example.behavioral.template_method;

public class ConcreteTemplateMethod extends TemplateMethod {
    @Override
    protected String step1() {
        return "ConcreteStep1";
    }

    @Override
    protected String step2() {
        return "ConcreteStep2";
    }
}
