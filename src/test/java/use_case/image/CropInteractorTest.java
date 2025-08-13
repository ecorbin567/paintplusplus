package use_case.image;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.crop.CropPresenter;
import use_case.image.crop.CropInteractor;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropRequestModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class CropInteractorTest {

    private DrawingViewModel drawingViewModel;
    private CanvasState canvasState;
    private CropOutputBoundary presenter;
    private CropInteractor interactor;

    @BeforeEach
    void setUp() {
        drawingViewModel = new DrawingViewModel();
        canvasState = new CanvasState();
        presenter = new CropPresenter(drawingViewModel); // real presenter; no mocking
        interactor = new CropInteractor(canvasState, presenter);
    }

    @Test
    void testSuccessfulCrop() {
        // Arrange
        BufferedImage dummyBufferedImage =
                new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Image initialImage = new Image(dummyBufferedImage);
        canvasState.setCurrentImage(initialImage);
        canvasState.getImportedImages().add(initialImage);

        // Act
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // Assert
        Image croppedImage = canvasState.getCurrentImage();
        ActionHistory history = canvasState.getActionHistory();

        assertNotNull(croppedImage, "Current image should exist after crop.");
        assertNotSame(initialImage, croppedImage, "Crop should replace with a new Image instance.");
        assertEquals(croppedImage, history.getCurrentState(),
                "ActionHistory current state should point to the cropped image.");

        assertEquals(50, croppedImage.getWidth(), "Cropped width should be 50.");
        assertEquals(50, croppedImage.getHeight(), "Cropped height should be 50.");
    }

    @Test
    void testCropFailureWhenNoImageExists() {
        // Arrange
        ActionHistory history = canvasState.getActionHistory();
        Object initialHistoryState = history.getCurrentState(); // likely null

        // Act
        CropRequestModel requestModel = new CropRequestModel(10, 10, 50, 50);
        interactor.execute(requestModel);

        // Assert
        assertNull(canvasState.getCurrentImage(),
                "Current image should remain null when cropping with no image.");
        assertEquals(initialHistoryState, history.getCurrentState(),
                "ActionHistory state should remain unchanged.");
    }
}
