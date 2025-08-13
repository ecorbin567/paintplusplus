package use_case.image;

import entity.CanvasState;
import entity.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.image.rotate.*;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class RotateInteractorTest {

    private CanvasState canvasState;
    private RotateInteractor interactor;
    private TestRotatePresenter presenter;

    @BeforeEach
    void setup() {
        canvasState = new CanvasState();
        presenter = new TestRotatePresenter();
        interactor = new RotateInteractor(canvasState, presenter);
    }

    @Test
    void testSuccessfulRotate90Degrees() {
        // Arrange
        BufferedImage originalImage = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Image image = new Image(originalImage);
        canvasState.setCurrentImage(image);

        // Act
        RotateRequestModel request = new RotateRequestModel(90);
        interactor.execute(request);

        // Assert
        Image rotatedImage = canvasState.getCurrentImage();
        assertNotNull(rotatedImage);
        assertEquals(50, rotatedImage.getWidth());
        assertEquals(100, rotatedImage.getHeight());
        assertEquals(rotatedImage, canvasState.getActionHistory().getCurrentState());

        assertTrue(presenter.successCalled);
        assertNull(presenter.errorMessage);
        assertEquals(rotatedImage, presenter.responseModel.getImage().get(0));
    }

    @Test
    void testRotateFailsWhenNoImageExists() {
        // Act
        RotateRequestModel request = new RotateRequestModel(90);
        interactor.execute(request);

        // Assert
        assertNull(canvasState.getCurrentImage());
        assertTrue(presenter.errorCalled);
        assertNotNull(presenter.errorMessage);
        assertEquals("No image to rotate", presenter.errorMessage);
    }

    public static class TestRotatePresenter implements RotateOutputBoundary {
        boolean successCalled = false;
        boolean errorCalled = false;
        String errorMessage = null;
        RotateResponseModel responseModel = null;

        @Override
        public void present(RotateResponseModel responseModel) {
            this.successCalled = true;
            this.responseModel = responseModel;
        }

        @Override
        public void presentError(String errorMessage) {
            this.errorCalled = true;
            this.errorMessage = errorMessage;
        }
    }
}