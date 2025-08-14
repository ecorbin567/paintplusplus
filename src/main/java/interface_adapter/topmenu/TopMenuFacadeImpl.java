package interface_adapter.topmenu;

import java.awt.image.BufferedImage;
import java.io.File;

import interface_adapter.topmenu.canvassize.ResizeCanvasController;
import interface_adapter.topmenu.history.HistoryController;
import interface_adapter.topmenu.save.SaveController;
import interface_adapter.topmenu.saveonline.save.SaveOnlineController;

public class TopMenuFacadeImpl implements TopMenuFacade {
    private final ResizeCanvasController sizeController;
    private final SaveController saveController;
    private final SaveOnlineController saveOnlineController;
    private final HistoryController historyController;

    public TopMenuFacadeImpl(ResizeCanvasController resize, SaveController save,
                             SaveOnlineController saveOnlineController,
                             HistoryController history) {
        sizeController = resize;
        saveController = save;
        this.saveOnlineController = saveOnlineController;
        historyController = history;
    }

    @Override
    public void zoomIn() {
        sizeController.handleZoomInButtonPress();
    }

    @Override
    public void zoomOut() {
        sizeController.handleZoomOutButtonPress();
    }

    @Override
    public void save(BufferedImage image, File file) {
        saveController.handleSaveButtonPress(image, file);
    }

    @Override
    public void saveOnline(BufferedImage image, String username) {
        saveOnlineController.handleSaveOnlineButtonPress(image, username);
    }

    @Override
    public void undo() {
        historyController.handleUndoButtonPress();
    }

    @Override
    public void redo() {
        historyController.handleRedoButtonPress();
    }
}
