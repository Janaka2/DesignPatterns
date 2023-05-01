package example;

import org.example.singalton.Singleton;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SingletonTest {
    @Test
    public void testSingleton() {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        assertSame(instance1, instance2);
    }
}