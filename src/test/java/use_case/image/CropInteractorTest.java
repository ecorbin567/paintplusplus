package use_case.image;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.Image;
import use_case.image.crop.CropInteractor;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropRequestModel;
import use_case.image.crop.CropResponseModel;

// Using JUnit 4, which is compatible with your project setup
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

/**
 * A simple unit test for the CropInteractor.
 */
public class CropInteractorTest {

    /**
     * A fake Presenter for testing. It just records what it was told to do.
     */
    private static class MockCropPresenter implements CropOutputBoundary {
        boolean successCalled = false;
        boolean errorCalled = false;
        Image resultImage = null;

        @Override
        public void present(CropResponseModel responseModel) {
            this.successCalled = true;
            this.resultImage = responseModel.getImage();
        }

        @Override
        public void presentError(String error) {
            this.errorCalled = true;
        }
    }

    /**
     * A fake DrawingCanvas for testing. We can tell it what image to return.
     */
    private static class MockDrawingCanvas extends DrawingCanvas {
        private Image currentImage;

        @Override
        public Image getCurrentImage() {
            return this.currentImage;
        }

        // Helper method for the test to set up the initial state
        public void setCurrentImageForTest(Image image) {
            this.currentImage = image;
        }
    }

    @Test
    public void testSuccessfulCrop() { // JUnit 4 tests must be public
        // 1. Arrange: Set up all the pieces for the test
        ActionHistory actionHistory = new ActionHistory();
        MockDrawingCanvas mockCanvas = new MockDrawingCanvas();
        MockCropPresenter mockPresenter = new MockCropPresenter();
        CropInteractor interactor = new CropInteractor(mockCanvas, mockPresenter, actionHistory);

        // Create a fake 100x100 image and put it on our mock canvas
        BufferedImage dummyBufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Image initialImage = new Image(dummyBufferedImage);
        mockCanvas.setCurrentImageForTest(initialImage);

        // 2. Act: Run the method we want to test
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // 3. Assert: Check if the results are correct
        assertTrue("The presenter's success method should have been called.", mockPresenter.successCalled);
        assertFalse("The presenter's error method should not have been called.", mockPresenter.errorCalled);

        // Check that the history was updated with the new cropped image
        assertEquals(mockPresenter.resultImage, actionHistory.getCurrentState());

        // Check that the new image has the correct cropped dimensions
        Image croppedImage = mockPresenter.resultImage;
        assertNotNull("The result image should not be null.", croppedImage);
        assertEquals("Cropped image width should be 50.", 50, croppedImage.getBufferedImage().getWidth());
        assertEquals("Cropped image height should be 50.", 50, croppedImage.getBufferedImage().getHeight());
    }

    @Test
    public void testCropFailureWhenNoImageExists() { // JUnit 4 tests must be public
        // 1. Arrange
        ActionHistory actionHistory = new ActionHistory();
        MockDrawingCanvas mockCanvas = new MockDrawingCanvas();
        MockCropPresenter mockPresenter = new MockCropPresenter();
        CropInteractor interactor = new CropInteractor(mockCanvas, mockPresenter, actionHistory);

        // Make sure there is no image on the canvas
        mockCanvas.setCurrentImageForTest(null);

        // 2. Act
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // 3. Assert
        assertTrue("The presenter's error method should have been called.", mockPresenter.errorCalled);
        assertFalse("The presenter's success method should not have been called.", mockPresenter.successCalled);
        assertNull("The action history should not have been changed.", actionHistory.getCurrentState());
    }
}