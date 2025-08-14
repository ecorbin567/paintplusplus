package app.topmenufactory;

import data_access.CanvasDataAccessInterface;
import interface_adapter.topmenu.saveonline.save.SaveOnlineController;
import use_case.topmenu.save.SaveOnlineInputBoundary;
import use_case.topmenu.save.SaveOnlineInteractor;

public final class SaveOnlineControllerFactory {
    /**
     * Factory function for creating the Save Controller.
     *
     * @param canvasDataAccessObject dao
     * @return the SaveController created
     */
    public static SaveOnlineController create(CanvasDataAccessInterface canvasDataAccessObject) {
        final SaveOnlineInputBoundary interactor = new SaveOnlineInteractor(canvasDataAccessObject);
        return new SaveOnlineController(interactor);
    }
}
