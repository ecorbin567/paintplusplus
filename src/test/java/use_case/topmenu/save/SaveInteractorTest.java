package use_case.topmenu.save;

import data_access.CanvasDataAccessInterface;
import entity.CanvasState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveInteractorTest {

    private FakeSaveFileGateway fileSaveGateway;
    private FakeCanvasDAO canvasDAO;
    private CanvasState canvasState;
    private SaveInteractor interactor;

    @BeforeEach
    void setUp() {
        fileSaveGateway = new FakeSaveFileGateway();
        canvasDAO = new FakeCanvasDAO();
        canvasState = new CanvasState();
        interactor = new SaveInteractor(canvasState, fileSaveGateway, canvasDAO);
    }

    @Test
    void save_success_updatesCanvasState_andCallsGateway(@TempDir Path tempDir) {
        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();
        SaveInputData input = new SaveInputData(image, file);

        assertNull(canvasState.getSavedImage());
        assertNull(canvasState.getSavedImageFile());
        assertEquals(0, fileSaveGateway.calls);

        interactor.save(input);

        assertEquals(1, fileSaveGateway.calls);
        assertSame(image, fileSaveGateway.lastImage);
        assertEquals(file, fileSaveGateway.lastFile);

        assertSame(image, canvasState.getSavedImage(), "CanvasState gets Save Image");
        assertEquals(file, canvasState.getSavedImageFile(), "CanvasState gets File");
    }

    @Test
    void save_whenGatewayThrowsIOException_doesNotMutateCanvasState(@TempDir Path tempDir) {
        BufferedImage image = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        File file = tempDir.resolve("out.png").toFile();
        SaveInputData input = new SaveInputData(image, file);

        fileSaveGateway.throwOnSave = true;

        assertNull(canvasState.getSavedImage());
        assertNull(canvasState.getSavedImageFile());

        interactor.save(input);

        assertEquals(1, fileSaveGateway.calls);
        assertSame(image, fileSaveGateway.lastImage);
        assertEquals(file, fileSaveGateway.lastFile);

        assertNull(canvasState.getSavedImage(), "Saved image should remain null");
        assertNull(canvasState.getSavedImageFile(), "File path should remain null");
    }

    @Test
    void saveCanvasOnline_delegatesToDAO() {

        String username = "alice";
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);

        interactor.saveCanvasOnline(username, image);

        assertEquals(1, canvasDAO.calls);
        assertEquals(username, canvasDAO.lastUsername);
        assertSame(image, canvasDAO.lastImage);
    }

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
            if (throwOnSave) throw new IOException("boom");
        }
    }

    private static class FakeCanvasDAO implements CanvasDataAccessInterface {
        int calls = 0;
        String lastUsername;
        BufferedImage lastImage;

        @Override
        public boolean saveCanvas(String username, BufferedImage image) {
            calls++;
            lastUsername = username;
            lastImage = image;
            return false;
        }

        @Override
        public List<BufferedImage> getAllCanvases(String username) {
            return List.of();
        }
    }
}
