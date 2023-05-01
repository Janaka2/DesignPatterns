package example;

// FluentApiPatternTest.java
import org.example.fluent_api_pattern.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FluentApiPatternTest {
    @Test
    public void fluentApiPatternTest() {
        Person person = new Person()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(30);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(30, person.getAge());
    }
}
