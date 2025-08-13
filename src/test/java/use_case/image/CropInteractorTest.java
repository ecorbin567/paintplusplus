package use_case.image;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.crop.CropPresenter;
import use_case.image.crop.CropInteractor;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropRequestModel;

// Using JUnit 5 (Jupiter)
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

/**
 * A simple unit test for the CropInteractor, styled after EraseInteractorTest.
 */
public class CropInteractorTest {

    // Initialize components as fields, similar to the provided example
    DrawingViewModel drawingViewModel = new DrawingViewModel();
    CanvasState canvasState = new CanvasState();
    CropOutputBoundary presenter = new CropPresenter(drawingViewModel);
    CropInteractor interactor = new CropInteractor(canvasState, presenter);

    @Test
    void testSuccessfulCrop() {
        // 1. Arrange: Set up the initial state
        BufferedImage dummyBufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Image initialImage = new Image(dummyBufferedImage);
        canvasState.setCurrentImage(initialImage);
        canvasState.getImportedImages().add(initialImage);

        // 2. Act: Run the method we want to test
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // 3. Assert: Check if the results are correct
        Image croppedImage = canvasState.getCurrentImage();
        ActionHistory actionHistory = canvasState.getActionHistory();

        assertNotNull(croppedImage, "The current image should not be null after a successful crop.");
        assertNotEquals(initialImage, croppedImage, "The current image should be a new, cropped image.");
        assertEquals(croppedImage, actionHistory.getCurrentState(), "The action history's current state should be the new cropped image.");

        // Check that the new image has the correct cropped dimensions
        assertEquals(50, croppedImage.getWidth(), "Cropped image width should be 50.");
        assertEquals(50, croppedImage.getHeight(), "Cropped image height should be 50.");
    }

    @Test
    void testCropFailureWhenNoImageExists() {
        // 1. Arrange
        // The canvasState is created with no current image by default.
        ActionHistory actionHistory = canvasState.getActionHistory();
        Object initialHistoryState = actionHistory.getCurrentState();

        // 2. Act
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // 3. Assert
        assertNull(canvasState.getCurrentImage(), "The current image should still be null.");
        assertEquals(initialHistoryState, actionHistory.getCurrentState(), "The action history should not have changed.");
    }
}