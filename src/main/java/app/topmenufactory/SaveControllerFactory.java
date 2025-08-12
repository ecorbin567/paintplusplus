package app.topmenufactory;

import data_access.CanvasDataAccessInterface;
import entity.CanvasState;
import interface_adapter.topmenu.save.SaveController;
import use_case.topmenu.save.SaveFileGateway;
import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInteractor;

public class SaveControllerFactory {
    public static SaveController create(CanvasState canvasState,
                                        SaveFileGateway imageSaveGateway,
                                        CanvasDataAccessInterface canvasDataAccessObject) {
        SaveInputBoundary interactor = new SaveInteractor(canvasState, imageSaveGateway, canvasDataAccessObject);
        return new SaveController(interactor);
    }
}
