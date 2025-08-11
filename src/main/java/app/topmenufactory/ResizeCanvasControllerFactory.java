package app.topmenufactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.topmenu.canvassize.ResizeCanvasController;
import interface_adapter.topmenu.canvassize.ResizeCanvasPresenter;
import use_case.topmenu.canvassize.SizeInputBoundary;
import use_case.topmenu.canvassize.SizeInteractor;
import use_case.topmenu.canvassize.SizeOutputBoundary;


public class ResizeCanvasControllerFactory {
    public static ResizeCanvasController create(CanvasState canvasState, DrawingViewModel drawingViewModel){
        SizeOutputBoundary presenter = new ResizeCanvasPresenter(drawingViewModel);
        SizeInputBoundary interactor = new SizeInteractor(canvasState, presenter);
        return new ResizeCanvasController(interactor);
    }
}
