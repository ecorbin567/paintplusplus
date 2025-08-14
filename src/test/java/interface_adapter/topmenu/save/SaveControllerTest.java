package interface_adapter.topmenu.save;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInputData;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class SaveControllerTest {

    static class FakeSaveInteractor implements SaveInputBoundary {
        int saveCalls = 0;
        SaveInputData lastInput;

        @Override
        public void save(SaveInputData inputData) {
            saveCalls++;
            lastInput = inputData;
        }
    }

    @Test
    void handleSaveButtonPress_delegatesToUseCaseWithSameImageAndFile(@TempDir Path tempDir) {
        FakeSaveInteractor fake = new FakeSaveInteractor();
        SaveController controller = new SaveController(fake);

        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();

        controller.handleSaveButtonPress(image, file);

        assertEquals(1, fake.saveCalls);
        assertNotNull(fake.lastInput);
        assertSame(image, fake.lastInput.image());
        assertEquals(file, fake.lastInput.file());
    }
}
