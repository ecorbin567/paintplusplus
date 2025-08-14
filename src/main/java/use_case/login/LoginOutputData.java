package use_case.login;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final boolean useCaseFailed;
    private final List<BufferedImage> userCanvases;

    public LoginOutputData(String username, boolean useCaseFailed, List<BufferedImage> userCanvases) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.userCanvases = userCanvases;
    }

    /**
     * Get username.
     * @return Logged in user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Serve the logged-in user's canvases.
     * @return all the canvases associated to this user
     */
    public List<BufferedImage> getUserCanvases() {
        return userCanvases;
    }
}
