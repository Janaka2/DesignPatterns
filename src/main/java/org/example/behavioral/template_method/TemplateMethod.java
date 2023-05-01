package org.example.behavioral.template_method;

public abstract class TemplateMethod {
    public final String execute() {
        String step1 = step1();
        String step2 = step2();
        String step3 = step3();
        return step1 + step2 + step3;
    }

    protected abstract String step1();

    protected abstract String step2();

    protected String step3() {
        return "CommonStep3";
    }
}
