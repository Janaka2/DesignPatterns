package org.example.event_sourcing_pattern;

// UserRepository.java
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, String> users = new HashMap<>();

    public void createUser(String userId, String userName) {
        users.put(userId, userName);
    }

    public String getUser(String userId) {
        return users.get(userId);
    }
}
