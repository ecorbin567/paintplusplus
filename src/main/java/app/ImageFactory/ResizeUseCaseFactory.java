package app.ImageFactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.resize.ResizeController;
import interface_adapter.image.resize.ResizePresenter;
import use_case.image.resize.ResizeInputBoundary;
import use_case.image.resize.ResizeInteractor;
import use_case.image.resize.ResizeOutputBoundary;

public class ResizeUseCaseFactory {
    public static ResizeController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel) {
        ResizeOutputBoundary presenter = new ResizePresenter(drawingViewModel);
        ResizeInputBoundary interactor = new ResizeInteractor(canvasState, presenter);
        return new ResizeController(interactor);
    }
}
