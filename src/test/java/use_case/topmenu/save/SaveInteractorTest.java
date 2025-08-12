package use_case.topmenu.save;

import entity.CanvasState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for SaveInteractor.
 */
class SaveInteractorTest {

    private CanvasState canvasState;
    private FakeSaveFileGateway fileGateway;
    private SaveInteractor interactor;

    @BeforeEach
    void setUp() {
        canvasState = new CanvasState();
        fileGateway = new FakeSaveFileGateway();
        interactor = new SaveInteractor(canvasState, fileGateway);
    }

    @Test
    void save_success_updatesCanvasState_andCallsGateway(@TempDir Path tempDir) {
        // Arrange
        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();
        SaveInputData input = new SaveInputData(image, file);

        // Precondition
        assertNull(canvasState.getSavedImage());
        assertNull(canvasState.getSavedImageFile());
        assertEquals(0, fileGateway.calls);

        // Act
        interactor.save(input);

        // Assert: gateway called with same args
        assertEquals(1, fileGateway.calls);
        assertSame(image, fileGateway.lastImage);
        assertEquals(file, fileGateway.lastFile);

        // Assert: state updated only after successful save
        assertSame(image, canvasState.getSavedImage(), "Saved image should be set");
        assertEquals(file, canvasState.getSavedImageFile(), "File path should be set");
    }

    @Test
    void save_whenGatewayThrowsIOException_doesNotMutateCanvasState(@TempDir Path tempDir) {
        // Arrange
        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();
        SaveInputData input = new SaveInputData(image, file);
        fileGateway.throwOnSave = true;

        // Precondition
        assertNull(canvasState.getSavedImage());
        assertNull(canvasState.getSavedImageFile());

        // Act: should catch internally (no exception thrown to caller)
        interactor.save(input);

        // Assert: gateway attempted
        assertEquals(1, fileGateway.calls);
        assertSame(image, fileGateway.lastImage);
        assertEquals(file, fileGateway.lastFile);

        // Assert: state unchanged due to exception
        assertNull(canvasState.getSavedImage(), "Saved image should remain null if save fails");
        assertNull(canvasState.getSavedImageFile(), "File path should remain null if save fails");
    }

    // --------- Test double (no Mockito needed) ---------

    private static class FakeSaveFileGateway implements SaveFileGateway {
        int calls = 0;
        BufferedImage lastImage;
        File lastFile;
        boolean throwOnSave = false;

        @Override
        public void saveImage(BufferedImage image, File file) throws IOException {
            calls++;
            lastImage = image;
            lastFile = file;
            if (throwOnSave) {
                throw new IOException("boom");
            }
            // No actual disk write.
        }
    }
}
