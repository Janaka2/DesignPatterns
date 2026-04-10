package org.example.data_consistency;

public record OutboxEvent(String aggregateId, String eventType, String payload) {
}
