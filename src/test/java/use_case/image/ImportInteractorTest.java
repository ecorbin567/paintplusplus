package use_case.image;

import entity.CanvasState;
import entity.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.image.import_image.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ImportInteractorTest {

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
        interactor.execute(new ImportRequestModel(new File("dummy/path/image.png")));

        Image image = canvasState.getCurrentImage();
        assertNotNull(image);
        assertEquals(1, canvasState.getImportedImages().size());
        assertEquals(image, canvasState.getImportedImages().get(0));
        assertEquals(image, canvasState.getActionHistory().getCurrentState());

        assertTrue(presenter.successCalled);
        assertNull(presenter.errorMessage);
    }

    @Test
    void testImportFailsWithIOException() {
        gateway.setShouldFail(true);
        interactor.execute(new ImportRequestModel(new File("fail/path/image.png")));

        assertNull(canvasState.getCurrentImage());
        assertTrue(canvasState.getImportedImages().isEmpty());

        assertTrue(presenter.errorCalled);
        assertNotNull(presenter.errorMessage);
        assertTrue(presenter.errorMessage.startsWith("Failed to import image"));
    }

    public static class TestImportGateway implements ImportGateway {
        private boolean fail = false;

        void setShouldFail(boolean shouldFail) {
            this.fail = shouldFail;
        }

        @Override
        public Image loadImage(File file) throws IOException {
            if (fail) throw new IOException("Simulated failure");
            return new Image(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        }
    }

    public static class TestImportPresenter implements ImportOutputBoundary {
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