package use_case.goback;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Output Data for the Go Back Use Case.
 */
public class GoBackOutputData {

    private final String username;
    private final List<BufferedImage> updatedCanvases;

    public GoBackOutputData(String username, List<BufferedImage> images) {
        this.username = username;
        this.updatedCanvases = images;
    }

    public String getUsername() {
        return username;
    }

    public List<BufferedImage> getUpdatedCanvases() {
        return updatedCanvases;
    }
}
