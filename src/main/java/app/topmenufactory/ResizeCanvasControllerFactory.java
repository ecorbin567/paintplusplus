package app.topmenufactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.topmenu.canvassize.ResizeCanvasController;
import interface_adapter.topmenu.canvassize.ResizeCanvasPresenter;
import use_case.topmenu.canvassize.SizeInputBoundary;
import use_case.topmenu.canvassize.SizeInteractor;
import use_case.topmenu.canvassize.SizeOutputBoundary;

public final class ResizeCanvasControllerFactory {
    /**
     * Factory function for creating the Resize Canvas Controller.
     *
     * @param canvasState      the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @return the ResizeCanvasController created
     */
    public static ResizeCanvasController create(CanvasState canvasState, DrawingViewModel drawingViewModel) {
        final SizeOutputBoundary presenter = new ResizeCanvasPresenter(drawingViewModel);
        final SizeInputBoundary interactor = new SizeInteractor(canvasState, presenter);
        return new ResizeCanvasController(interactor);
    }
}
