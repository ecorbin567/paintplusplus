package use_case.getcanvas;

import entity.User;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * DAO for the Login Use Case.
 */
public interface GetCanvasUserDataAccessInterface {

    /**
     * Add new document to user's repository
     * @param image is the image to upload
     * @return
     */
    boolean saveCanvas(String username, BufferedImage image);

    /**
     * Get a certain project from user's repository.
     * @param id of the canvas
     * @return the action history of the desired drawing
     */
    BufferedImage findCanvasById(String username, int id);

    List<BufferedImage> getAllCanvases(String username);
}
