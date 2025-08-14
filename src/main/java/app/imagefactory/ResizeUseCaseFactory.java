package app.imagefactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.resize.ResizeController;
import interface_adapter.image.resize.ResizePresenter;
import use_case.image.resize.ResizeInputBoundary;
import use_case.image.resize.ResizeInteractor;
import use_case.image.resize.ResizeOutputBoundary;

public final class ResizeUseCaseFactory {
    private ResizeUseCaseFactory() {
        // Unneeded
    }

    /**
     * Factory function for creating the Resize Use Case.
     *
     * @param canvasState      the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @return the ResizeController created
     */
    public static ResizeController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel) {
        final ResizeOutputBoundary presenter = new ResizePresenter(drawingViewModel);
        final ResizeInputBoundary interactor = new ResizeInteractor(canvasState, presenter);
        return new ResizeController(interactor);
    }
}
