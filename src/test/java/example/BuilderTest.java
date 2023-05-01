package example;

import org.example.creational.builder.CheeseBurger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuilderTest {

    @Test
    public void testBuilder() {
        CheeseBurger cheeseBurger = new CheeseBurger.Builder()
                .bun("Brioche")
                .patty("Beef")
                .sauce("Mayo")
                .toppings("Lettuce, Tomato, Onion")
                .cheese("Cheddar")
                .build();

        assertNotNull(cheeseBurger);
        assertEquals("Brioche", cheeseBurger.getBun());
        assertEquals("Beef", cheeseBurger.getPatty());
        assertEquals("Mayo", cheeseBurger.getSauce());
        assertEquals("Lettuce, Tomato, Onion", cheeseBurger.getToppings());
        assertEquals("Cheddar", cheeseBurger.getCheese());
    }
}