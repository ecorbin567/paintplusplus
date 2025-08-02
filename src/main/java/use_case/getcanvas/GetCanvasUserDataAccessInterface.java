package use_case.getcanvas;

import data_access.UserDataAccessInterface;
import entity.ActionHistory;
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
    boolean saveCanvas(User user, BufferedImage image);

    /**
     * Get a certain project from user's repository.
     * @param id of the canvas
     * @return the action history of the desired drawing
     */
    BufferedImage findCanvasById(User user, int id);

    List<BufferedImage> getAllCanvases(User user);
}
