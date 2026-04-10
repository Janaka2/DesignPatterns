package org.example.enterprise.resilience;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class IdempotencyKeyStore {
    private final Map<String, String> processedResponses = new HashMap<>();

    public synchronized String execute(String key, java.util.function.Supplier<String> action) {
        if (processedResponses.containsKey(key)) {
            return processedResponses.get(key);
        }

        String result = action.get();
        processedResponses.put(key, result);
        return result;
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(processedResponses.get(key));
    }
}
