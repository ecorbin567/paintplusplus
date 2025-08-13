package use_case.image;

import entity.CanvasState;
import entity.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.image.resize.*;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class ResizeInteractorTest {

    private CanvasState canvasState;
    private ResizeInteractor interactor;
    private TestResizePresenter presenter;

    @BeforeEach
    void setup() {
        canvasState = new CanvasState();
        presenter = new TestResizePresenter();
        interactor = new ResizeInteractor(canvasState, presenter);
    }

    @Test
    void testSuccessfulResize() {
        // Arrange: set a dummy 100x100 image
        BufferedImage originalImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Image image = new Image(originalImage);
        canvasState.setCurrentImage(image);

        // Act: resize to 50x80
        ResizeRequestModel request = new ResizeRequestModel(50, 80);
        interactor.execute(request);

        // Assert
        Image resizedImage = canvasState.getCurrentImage();
        assertNotNull(resizedImage);
        assertEquals(50, resizedImage.getWidth());
        assertEquals(80, resizedImage.getHeight());

        // Make sure action history is updated
        assertEquals(resizedImage, canvasState.getActionHistory().getCurrentState());

        // Presenter should be called with correct image
        assertTrue(presenter.successCalled);
        assertNull(presenter.errorMessage);
        assertNotNull(presenter.responseModel);
        assertEquals(1, presenter.responseModel.getImages().size());
        assertEquals(resizedImage, presenter.responseModel.getImages().get(0));
    }

    @Test
    void testResizeFailsWhenNoImageExists() {
        // Act: try resizing when no image exists
        ResizeRequestModel request = new ResizeRequestModel(50, 80);
        interactor.execute(request);

        // Assert
        assertNull(canvasState.getCurrentImage());
        assertTrue(presenter.errorCalled);
        assertNotNull(presenter.errorMessage);
        assertEquals("No image to resize", presenter.errorMessage);
    }

    public static class TestResizePresenter implements ResizeOutputBoundary {
        boolean successCalled = false;
        boolean errorCalled = false;
        String errorMessage = null;
        ResizeResponseModel responseModel = null;

        @Override
        public void present(ResizeResponseModel responseModel) {
            successCalled = true;
            this.responseModel = responseModel;
        }

        @Override
        public void presentError(String error) {
            errorCalled = true;
            this.errorMessage = error;
        }
    }
}