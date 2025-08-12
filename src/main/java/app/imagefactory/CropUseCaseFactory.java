package app.imagefactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.crop.CropController;
import interface_adapter.image.crop.CropPresenter;
import use_case.image.crop.CropInputBoundary;
import use_case.image.crop.CropInteractor;
import use_case.image.crop.CropOutputBoundary;

/**
 * This class contains the static factory function for creating the Crop Use Case.
 */
public class CropUseCaseFactory {
    private CropUseCaseFactory(){
        //Unneeded
    }

    /**
     * Factory function for creating the Crop Use Case.
     * @param canvasState the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @return the CropController created
     */
    public static CropController create(CanvasState canvasState, DrawingViewModel drawingViewModel) {
        final CropOutputBoundary presenter = new CropPresenter(drawingViewModel);
        final CropInputBoundary interactor = new CropInteractor(canvasState, presenter);
        return new CropController(interactor);
    }
}
