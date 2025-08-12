package use_case.topmenu.save;

import data_access.CanvasDataAccessInterface;

import java.awt.image.BufferedImage;

public class SaveOnlineInteractor implements SaveOnlineInputBoundary{
    private final CanvasDataAccessInterface canvasDataAccessObject;

    public SaveOnlineInteractor(CanvasDataAccessInterface canvasDataAccessObject) {
        this.canvasDataAccessObject = canvasDataAccessObject;
    }

    @Override
    public void saveCanvasOnline(String username, BufferedImage image) {
        canvasDataAccessObject.saveCanvas(username, image);
    }
}
