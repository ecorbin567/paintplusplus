package app.topmenufactory;

import entity.CanvasState;
import interface_adapter.topmenu.save.SaveController;
import use_case.topmenu.save.SaveFileGateway;
import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInteractor;

public class SaveControllerFactory {
    public static SaveController create(CanvasState canvasState, SaveFileGateway imageSaveGateway) {
        SaveInputBoundary interactor = new SaveInteractor(canvasState, imageSaveGateway);
        return new SaveController(interactor);
    }
}
