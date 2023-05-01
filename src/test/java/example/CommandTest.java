package example;

import org.example.behavioral.command.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void testCommandPattern() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Light light = new Light();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);

        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(lightOnCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(lightOffCommand);
        remoteControl.pressButton();

        String expectedOutput = "Light is on" + System.lineSeparator() +
                "Light is off" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}
