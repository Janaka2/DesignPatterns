package org.example.data_consistency;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChoreographySaga {
    private final List<Consumer<String>> handlers = new ArrayList<>();

    public void subscribe(Consumer<String> handler) {
        handlers.add(handler);
    }

    public void publish(String eventType) {
        for (Consumer<String> handler : handlers) {
            handler.accept(eventType);
        }
    }
}
