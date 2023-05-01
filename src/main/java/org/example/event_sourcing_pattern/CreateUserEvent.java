package org.example.event_sourcing_pattern;

// CreateUserEvent.java
public class CreateUserEvent implements Event {
    private String userId;
    private String userName;
    private UserRepository userRepository;

    public CreateUserEvent(String userId, String userName, UserRepository userRepository) {
        this.userId = userId;
        this.userName = userName;
        this.userRepository = userRepository;
    }

    @Override
    public void process() {
        userRepository.createUser(userId, userName);
    }
}
