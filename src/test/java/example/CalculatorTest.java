package example;

import org.example.solid.o.Addition;
import org.example.solid.o.Calculator;
import org.example.solid.o.Subtraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    public void testCalculator() {
        Calculator calculator = new Calculator();
        double a = 10;
        double b = 5;

        assertEquals(15, calculator.calculate(new Addition(), a, b), 0.001);
        assertEquals(5, calculator.calculate(new Subtraction(), a, b), 0.001);
    }
}
