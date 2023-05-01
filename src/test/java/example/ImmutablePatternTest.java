package example;

// ImmutablePatternTest.java
import org.example.immutable_pattern.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImmutablePatternTest {
    @Test
    public void immutablePatternTest() {
        Person person = new Person("John", "Doe", 30);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(30, person.getAge());
    }
}

