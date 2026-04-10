package example;

import org.example.fluent_api_pattern.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FluentApiPatternTest {

    @Test
    public void fluentApiPatternTest() {
        Person person = Person.builder()
                .withFirstName("John")
                .withLastName("Doe")
                .withAge(30);

        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getLastName());
        assertEquals(30, person.getAge());
        assertEquals("John Doe", person.fullName());
        assertTrue(person.isAdult());
    }

    @Test
    public void backwardCompatibleSettersStillSupportFluentStyle() {
        Person person = new Person()
                .setFirstName("Jane")
                .setLastName("Smith")
                .setAge(15);

        assertEquals("Jane Smith", person.fullName());
        assertFalse(person.isAdult());
    }

    @Test
    public void negativeAgeIsRejected() {
        Person person = new Person();

        assertThrows(IllegalArgumentException.class, () -> person.withAge(-1));
    }
}
