package org.example.event_sourcing_pattern;

// EventStore.java
import java.util.ArrayList;
import java.util.List;

public class EventStore {
    private List<Event> events = new ArrayList<>();

    public void store(Event event) {
        events.add(event);
        event.process();
    }
}
