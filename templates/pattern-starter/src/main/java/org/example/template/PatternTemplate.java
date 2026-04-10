package org.example.template;

/**
 * Minimal starter class for a new pattern contribution.
 */
public class PatternTemplate {
    private final String input;

    public PatternTemplate(String input) {
        this.input = input;
    }

    public String execute() {
        return "processed:" + input;
    }
}
