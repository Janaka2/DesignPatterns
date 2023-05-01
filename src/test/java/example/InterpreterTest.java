package example;

import org.example.behavioral.interpreter.Expression;
import org.example.behavioral.interpreter.ExpressionParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    @Test
    public void testInterpreterPattern() {
        String expression = "7 3 - 2 1 + +";
        ExpressionParser parser = new ExpressionParser();
        Expression result = parser.parse(expression);

        assertEquals(7, result.interpret());
    }
}
