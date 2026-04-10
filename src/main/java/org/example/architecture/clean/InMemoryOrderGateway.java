package org.example.architecture.clean;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderGateway implements OrderGateway {
    private final Map<String, String> statuses = new HashMap<>();

    @Override
    public void save(String orderId, String status) {
        statuses.put(orderId, status);
    }

    @Override
    public String getStatus(String orderId) {
        return statuses.get(orderId);
    }
}
