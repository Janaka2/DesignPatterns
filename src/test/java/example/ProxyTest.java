package example;

import org.example.structural.proxy.Image;
import org.example.structural.proxy.ProxyImage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProxyTest {
    @Test
    public void testProxyPattern() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Image image = new ProxyImage("test_image.jpg");

        // First time display: image will be loaded from disk
        image.display();
        System.out.println();

        // Second time display: image will not be loaded from disk
        image.display();

        String expectedOutput = "Loading test_image.jpg" + System.lineSeparator() +
                "Displaying test_image.jpg" + System.lineSeparator() +
                System.lineSeparator() +
                "Displaying test_image.jpg" + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}

