package example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class SingletonModernPackageTest {

    @Test
    void testSingleton() {
        org.example.creational.singleton.Singleton instance1 = org.example.creational.singleton.Singleton.getInstance();
        org.example.creational.singleton.Singleton instance2 = org.example.creational.singleton.Singleton.getInstance();
        assertSame(instance1, instance2);
    }
}
