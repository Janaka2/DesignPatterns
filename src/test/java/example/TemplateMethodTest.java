package example;

import org.example.behavioral.template_method.ConcreteTemplateMethod;
import org.example.behavioral.template_method.TemplateMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateMethodTest {
    @Test
    public void testTemplateMethodPattern() {
        TemplateMethod templateMethod = new ConcreteTemplateMethod();
        String result = templateMethod.execute();
        assertEquals("ConcreteStep1ConcreteStep2CommonStep3", result);
    }
}
