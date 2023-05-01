package example;

import org.example.behavioral.observer.ConcreteObserver;
import org.example.behavioral.observer.Subject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObserverTest {
    @Test
    public void testObserverPattern() {
        Subject subject = new Subject();
        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        subject.notifyObservers("Hello!");

        assertEquals("Hello!", observer1.getObserverState());
        assertEquals("Hello!", observer2.getObserverState());
    }
}
