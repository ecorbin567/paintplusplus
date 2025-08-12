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
        int saveOnlineCalls = 0;

        SaveInputData lastInput;
        String lastUsername;
        BufferedImage lastOnlineImage;

        @Override
        public void save(SaveInputData inputData) {
            saveCalls++;
            lastInput = inputData;
        }

        @Override
        public void saveCanvasOnline(String username, BufferedImage image) {
            saveOnlineCalls++;
            lastUsername = username;
            lastOnlineImage = image;
        }
    }

    @Test
    void handleSaveButtonPress_delegatesToUseCaseWithSameImageAndFile(@TempDir Path tempDir) {
        FakeSaveInteractor fake = new FakeSaveInteractor();
        SaveController controller = new SaveController(fake);

        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();

        controller.handleSaveButtonPress(image, file);

        assertEquals(1, fake.saveCalls, "save() should be called exactly once");
        assertNotNull(fake.lastInput, "save() should receive a non-null SaveInputData");
        assertSame(image, fake.lastInput.image(), "Controller should pass the same BufferedImage");
        assertEquals(file, fake.lastInput.file(), "Controller should pass the same File");
    }

    @Test
    void handleSaveOnlineButtonPress_delegatesToUseCaseWithSameUsernameAndImage() {
        FakeSaveInteractor fake = new FakeSaveInteractor();
        SaveController controller = new SaveController(fake);

        String username = "alice";
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);

        controller.handleSaveOnlineButtonPress(image, username);

        assertEquals(1, fake.saveOnlineCalls, "saveCanvasOnline() should be called exactly once");
        assertEquals(username, fake.lastUsername, "Username should be forwarded unchanged");
        assertSame(image, fake.lastOnlineImage, "Image should be forwarded unchanged");
    }
}
