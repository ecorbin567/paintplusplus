package app.topmenufactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.topmenu.history.HistoryController;
import interface_adapter.topmenu.history.HistoryPresenter;
import use_case.topmenu.history.HistoryInputBoundary;
import use_case.topmenu.history.HistoryInteractor;
import use_case.topmenu.history.HistoryOutputBoundary;

public class HistoryControllerFactory {
    public static HistoryController create(CanvasState canvasState, DrawingViewModel drawingViewModel){
        HistoryOutputBoundary presenter = new HistoryPresenter(drawingViewModel);
        HistoryInputBoundary interactor = new HistoryInteractor(canvasState, presenter);
        return new HistoryController(interactor);
    }
}
