package data_access;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * General Canvas Interface for simpilicity.
 * Adds freedom to further delete interfaces in the future for cleaning up.
 */
public interface CanvasDataAccessInterface {
    /**
     * Add new document to user's repository.
     *
     * @param username The user's username
     * @param image    is the image to upload
     * @return a boolean indicating whether username is null
     */
    boolean saveCanvas(String username, BufferedImage image);

    /**
     * Get all projects from user's repository.
     *
     * @param username name of said user
     * @return the screenshot of the projects
     */
    List<BufferedImage> getAllCanvases(String username);
}
