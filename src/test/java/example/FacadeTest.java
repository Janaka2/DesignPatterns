package example;

import org.example.structural.facade.Facade;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FacadeTest {
    @Test
    public void testFacadePattern() {
        Facade facade = new Facade();
        String expectedResult = "Subsystem A, Operation A -> Subsystem B, Operation B -> Subsystem C, Operation C";
        assertEquals(expectedResult, facade.performComplexOperation());
    }
}
