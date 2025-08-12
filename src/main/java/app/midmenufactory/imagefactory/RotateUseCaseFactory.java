package app.midmenufactory.imagefactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.midmenu.image.rotate.RotateController;
import interface_adapter.midmenu.image.rotate.RotatePresenter;
import use_case.image.rotate.RotateInputBoundary;
import use_case.image.rotate.RotateInteractor;
import use_case.image.rotate.RotateOutputBoundary;

public class RotateUseCaseFactory {
    public static RotateController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel) {
        RotateOutputBoundary presenter = new RotatePresenter(drawingViewModel);
        RotateInputBoundary interactor = new RotateInteractor(canvasState, presenter);
        return new RotateController(interactor);
    }
}
