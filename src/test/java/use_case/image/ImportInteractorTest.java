package use_case.image;

import entity.CanvasState;
import entity.Image;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.import_image.ImportPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.image.import_image.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImportInteractorTest {

    private CanvasState canvasState;
    private TestImportGateway gateway;
    private TestImportPresenter presenter;
    private ImportInteractor interactor;

    @BeforeEach
    void setup() {
        canvasState = new CanvasState();
        gateway = new TestImportGateway();
        presenter = new TestImportPresenter();
        interactor = new ImportInteractor(gateway, presenter, canvasState);
    }

    @Test
    void testSuccessfulImport() {
        // 1. Arrange
        File dummyFile = new File("dummy/path/image.png");
        ImportRequestModel requestModel = new ImportRequestModel(dummyFile);

        // 2. Act
        interactor.execute(requestModel);

        // 3. Assert
        Image currentImage = canvasState.getCurrentImage();
        assertNotNull(currentImage, "Image should be imported and set as current image.");

        List<Image> importedImages = canvasState.getImportedImages();
        assertEquals(1, importedImages.size(), "Imported images list should contain one image.");
        assertEquals(currentImage, importedImages.get(0), "Imported image should match the current image.");

        assertEquals(currentImage, canvasState.getActionHistory().getCurrentState(), "Action history should reflect current image.");

        assertTrue(presenter.successCalled, "Presenter's present() should be called on success.");
        assertNull(presenter.errorMessage, "No error message should be set on success.");
    }

    @Test
    void testImportFailsWithIOException() {
        // 1. Arrange
        File badFile = new File("fail/this/import.png");
        gateway.setShouldFail(true);
        ImportRequestModel requestModel = new ImportRequestModel(badFile);

        // 2. Act
        interactor.execute(requestModel);

        // 3. Assert
        assertNull(canvasState.getCurrentImage(), "No image should be imported on failure.");
        assertEquals(0, canvasState.getImportedImages().size(), "Imported images list should remain empty.");

        assertTrue(presenter.errorCalled, "Presenter's presentError() should be called on failure.");
        assertNotNull(presenter.errorMessage, "An error message should be reported.");
        assertTrue(presenter.errorMessage.startsWith("Failed to import image"), "Error message should mention failure.");
    }

    static class TestImportGateway implements ImportGateway {
        private boolean shouldFail = false;

        public void setShouldFail(boolean fail) {
            this.shouldFail = fail;
        }

        @Override
        public Image loadImage(File file) throws IOException {
            if (shouldFail) {
                throw new IOException("Simulated load failure.");
            }
            BufferedImage dummyImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            return new Image(dummyImage);
        }
    }

    static class TestImportPresenter implements ImportOutputBoundary {
        boolean successCalled = false;
        boolean errorCalled = false;
        String errorMessage = null;

        @Override
        public void present(ImportResponseModel responseModel) {
            successCalled = true;
        }

        @Override
        public void presentError(String errorMessage) {
            errorCalled = true;
            this.errorMessage = errorMessage;
        }
    }
}