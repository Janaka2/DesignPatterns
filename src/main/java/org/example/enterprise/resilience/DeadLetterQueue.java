package org.example.enterprise.resilience;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeadLetterQueue {
    private final List<String> failedMessages = new ArrayList<>();

    public synchronized void publish(String message) {
        failedMessages.add(message);
    }

    public synchronized List<String> messages() {
        return Collections.unmodifiableList(new ArrayList<>(failedMessages));
    }
}
