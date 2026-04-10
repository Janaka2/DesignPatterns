package example;

import org.example.behavioral.observer.ConcreteObserver;
import org.example.behavioral.observer.Subject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {
    @Test
    public void testObserverPattern() {
        Subject subject = new Subject();
        ConcreteObserver observer1 = new ConcreteObserver("obs-1");
        ConcreteObserver observer2 = new ConcreteObserver("obs-2");

        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer1);

        subject.notifyObservers("Hello!");

        assertEquals(2, subject.observerCount());
        assertEquals("Hello!", observer1.getObserverState());
        assertEquals("Hello!", observer2.getObserverState());
    }
}
