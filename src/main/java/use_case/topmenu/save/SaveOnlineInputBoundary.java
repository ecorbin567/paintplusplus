package use_case.topmenu.save;

import java.awt.image.BufferedImage;

public interface SaveOnlineInputBoundary {
    /**
     * Save online button.
     *
     * @param username of the user
     * @param image    to save
     */
    void saveCanvasOnline(String username, BufferedImage image);
}
