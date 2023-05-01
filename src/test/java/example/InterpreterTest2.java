package example;

import org.example.behavioral.interpreter.Expression;
import org.example.behavioral.interpreter.ExpressionParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest2 {
    @Test
    public void testInterpreterPattern() {
        String expression = "7 3 2 + -"; // This evaluates to (7 - (3 + 2)) = 7 - 5 = 2
        ExpressionParser parser = new ExpressionParser();
        Expression result = parser.parse(expression);

        assertEquals(2, result.interpret());
    }
}
