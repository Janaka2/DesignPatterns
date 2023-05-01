package example;


import org.example.behavioral.memento.Caretaker;
import org.example.behavioral.memento.Originator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MementoTest {
    @Test
    public void testMementoPattern() {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState("State1");
        originator.setState("State2");
        caretaker.setMemento(originator.saveStateToMemento());

        originator.setState("State3");
        // We no longer save the "State3" to the caretaker here.

        originator.setState("State4");

        // We restore the previous state "State2" from the caretaker.
        originator.getStateFromMemento(caretaker.getMemento());
        assertEquals("State2", originator.getState());
    }
}

