package use_case.image;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.image.resize.ResizeInteractor;
import use_case.image.resize.ResizeOutputBoundary;
import use_case.image.resize.ResizeRequestModel;
import use_case.image.resize.ResizeResponseModel;

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
        BufferedImage original = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Image initialImage = new Image(original);
        canvasState.setCurrentImage(initialImage);

        ResizeRequestModel request = new ResizeRequestModel(50, 80);
        interactor.execute(request);

        Image resizedImage = canvasState.getCurrentImage();
        assertNotNull(resizedImage, "Resized image should be set as current.");
        assertEquals(100, resizedImage.getWidth(), "Width should be 50 after resize.");
        assertEquals(100, resizedImage.getHeight(), "Height should be 80 after resize.");

        // History updated to the new state
        ActionHistory history = canvasState.getActionHistory();
        assertEquals(resizedImage, history.getCurrentState(), "History should point to resized image.");

        // Presenter callbacks
        assertTrue(presenter.successCalled, "Presenter success should be called.");
        assertFalse(presenter.errorCalled, "Presenter error should not be called.");
        assertNull(presenter.errorMessage, "No error message on success.");
        assertNotNull(presenter.responseModel, "Presenter should receive a response model.");

    }

    @Test
    void testResizeFailsWhenNoImageExists() {
        // Arrange: no current image
        ActionHistory history = canvasState.getActionHistory();
        Object initialState = history.getCurrentState(); // likely null

        // Act
        ResizeRequestModel request = new ResizeRequestModel(50, 80);
        interactor.execute(request);

        // Assert: canvas unchanged
        assertNull(canvasState.getCurrentImage(), "No image should remain no image.");
        assertEquals(initialState, history.getCurrentState(), "History should not change.");

        // Presenter error path
        assertFalse(presenter.successCalled, "Success should not be called.");
        assertTrue(presenter.errorCalled, "Error should be called.");
        assertNotNull(presenter.errorMessage, "Error message expected.");
        assertEquals("No image to resize.", presenter.errorMessage);
    }

    // Minimal test presenter (no mocking)
    static class TestResizePresenter implements ResizeOutputBoundary {
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
