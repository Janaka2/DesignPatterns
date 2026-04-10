package example;

import org.example.creational.builder.CheeseBurger;
import org.example.creational.builder.VeggieBurger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuilderTest {

    @Test
    public void testCheeseBurgerBuilder() {
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

    @Test
    public void testVeggieBurgerBuilder() {
        VeggieBurger veggieBurger = new VeggieBurger.Builder()
                .bun("Whole Wheat")
                .patty("Black Bean")
                .sauce("Hummus")
                .toppings("Pickles, Arugula")
                .vegan(true)
                .build();

        assertNotNull(veggieBurger);
        assertEquals("Whole Wheat", veggieBurger.getBun());
        assertEquals("Black Bean", veggieBurger.getPatty());
        assertEquals("Hummus", veggieBurger.getSauce());
        assertEquals("Pickles, Arugula", veggieBurger.getToppings());
        assertTrue(veggieBurger.isVegan());
    }

    @Test
    public void testBuilderRequiresBunAndPatty() {
        IllegalStateException missingPatty = assertThrows(IllegalStateException.class,
                () -> new CheeseBurger.Builder()
                        .bun("Brioche")
                        .build());

        assertEquals("Patty is required", missingPatty.getMessage());

        IllegalStateException missingBun = assertThrows(IllegalStateException.class,
                () -> new CheeseBurger.Builder()
                        .patty("Beef")
                        .build());

        assertEquals("Bun is required", missingBun.getMessage());
    }
}
