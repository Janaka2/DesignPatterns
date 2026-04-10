package org.example.ai;

import java.util.List;

public class FallbackModelStrategy implements LlmClient {

    private final List<LlmClient> models;

    public FallbackModelStrategy(List<LlmClient> models) {
        this.models = models;
    }

    @Override
    public String complete(String prompt) {
        RuntimeException lastError = null;
        for (LlmClient model : models) {
            try {
                String response = model.complete(prompt);
                if (response != null && !response.isBlank()) {
                    return response;
                }
            } catch (RuntimeException ex) {
                lastError = ex;
            }
        }

        if (lastError != null) {
            throw lastError;
        }
        throw new IllegalStateException("No model produced a response");
    }
}
