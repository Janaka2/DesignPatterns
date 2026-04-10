package org.example.data_consistency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionalOutbox {
    private final List<OutboxEvent> outbox = new ArrayList<>();

    public synchronized void saveWithEvent(String entityId, Runnable entityMutation, OutboxEvent event) {
        entityMutation.run();
        outbox.add(event);
    }

    public synchronized List<OutboxEvent> pendingEvents() {
        return Collections.unmodifiableList(new ArrayList<>(outbox));
    }

    public synchronized void markDispatched(OutboxEvent event) {
        outbox.remove(event);
    }
}
