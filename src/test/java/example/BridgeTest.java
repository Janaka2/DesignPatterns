package example;

import org.example.structural.bridge.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BridgeTest {
    @Test
    public void testBridgePattern() {
        Theme lightTheme = new LightTheme();
        Theme darkTheme = new DarkTheme();

        Platform webPlatformLight = new WebPlatform(lightTheme);
        Platform webPlatformDark = new WebPlatform(darkTheme);
        Platform mobilePlatformLight = new MobilePlatform(lightTheme);
        Platform mobilePlatformDark = new MobilePlatform(darkTheme);

        assertEquals("Web platform in Light theme.", webPlatformLight.getContent());
        assertEquals("Web platform in Dark theme.", webPlatformDark.getContent());
        assertEquals("Mobile platform in Light theme.", mobilePlatformLight.getContent());
        assertEquals("Mobile platform in Dark theme.", mobilePlatformDark.getContent());
    }
}
