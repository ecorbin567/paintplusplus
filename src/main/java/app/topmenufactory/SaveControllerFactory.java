package app.topmenufactory;

import data_access.CanvasDataAccessInterface;
import entity.CanvasState;
import interface_adapter.topmenu.save.SaveController;
import use_case.topmenu.save.SaveFileGateway;
import use_case.topmenu.save.SaveInputBoundary;
import use_case.topmenu.save.SaveInteractor;

public class SaveControllerFactory {
    /**
     * Factory function for creating the Save Controller.
     * @param canvasState the CanvasState
     * @param imageSaveGateway the SaveFileGateway
     * @return the SaveController created
     */
    public static SaveController create(CanvasState canvasState, SaveFileGateway imageSaveGateway) {
        final SaveInputBoundary interactor = new SaveInteractor(canvasState, imageSaveGateway);
        return new SaveController(interactor);
    }
}
