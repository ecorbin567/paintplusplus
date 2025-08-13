package interface_adapter.topmenu.saveonline.save;

import use_case.topmenu.save.SaveOnlineInputBoundary;

import java.awt.image.BufferedImage;

public class SaveOnlineController {
    SaveOnlineInputBoundary interactor;
    public SaveOnlineController(SaveOnlineInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Save online button press
     * @param image to save
     * @param username of user
     */
    public void handleSaveOnlineButtonPress(BufferedImage image, String username) {
        interactor.saveCanvasOnline(username, image);
    }
}
