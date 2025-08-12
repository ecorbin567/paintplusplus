package app.midmenufactory.imagefactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.rotate.RotateController;
import interface_adapter.image.rotate.RotatePresenter;
import use_case.image.rotate.RotateInputBoundary;
import use_case.image.rotate.RotateInteractor;
import use_case.image.rotate.RotateOutputBoundary;

public class RotateUseCaseFactory {
    /**
     * Factory function for creating the Rotate Use Case.
     * @param canvasState the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @return the RotateController created
     */
    public static RotateController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel) {
        final RotateOutputBoundary presenter = new RotatePresenter(drawingViewModel);
        final RotateInputBoundary interactor = new RotateInteractor(canvasState, presenter);
        return new RotateController(interactor);
    }
}
