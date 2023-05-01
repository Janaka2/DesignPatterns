package example;

import org.example.solid.d.Application;
import org.example.solid.d.Storage;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class DependencyInversionTest {
    @Test
    public void testDependencyInversion() {
        String testData = "Test data";
        Storage storage = mock(Storage.class);

        Application application = new Application(storage);
        application.saveData(testData);

        verify(storage).save(testData);
    }
}
