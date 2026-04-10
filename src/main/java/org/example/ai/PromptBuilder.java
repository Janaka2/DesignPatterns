package org.example.ai;

import java.util.HashMap;
import java.util.Map;

public class PromptBuilder {

    private final String template;
    private final Map<String, String> values = new HashMap<>();

    public PromptBuilder(String template) {
        this.template = template;
    }

    public PromptBuilder with(String key, String value) {
        values.put(key, value);
        return this;
    }

    public String build() {
        String built = template;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            built = built.replace("{{" + entry.getKey() + "}}", entry.getValue());
        }
        return built;
    }
}
