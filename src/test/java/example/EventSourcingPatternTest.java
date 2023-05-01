package example;

// EventSourcingPatternTest.java
import org.example.event_sourcing_pattern.CreateUserEvent;
import org.example.event_sourcing_pattern.EventStore;
import org.example.event_sourcing_pattern.UserRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventSourcingPatternTest {
    @Test
    public void eventSourcingPatternTest() {
        UserRepository userRepository = new UserRepository();
        EventStore eventStore = new EventStore();

        CreateUserEvent createUserEvent = new CreateUserEvent("1", "John Doe", userRepository);
        eventStore.store(createUserEvent);

        String userName = userRepository.getUser("1");
        assertEquals("John Doe", userName);
    }
}

