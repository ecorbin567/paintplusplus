package interface_adapter.topmenu;

import java.awt.image.BufferedImage;
import java.io.File;

public interface TopMenuFacade {
    void zoomIn();

    void zoomOut();

    void save(BufferedImage image, File file);

    /**
     * Save a canvas to the user's account
     *
     * @param image    canvas to save
     * @param username user
     */
    void saveOnline(BufferedImage image, String username);

    void undo();

    void redo();
}
