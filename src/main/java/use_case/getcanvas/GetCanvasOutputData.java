package use_case.getcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Output Data for the Login Use Case.
 */
public class GetCanvasOutputData {

    private final String username;
    private final List<BufferedImage> canvases;

    public GetCanvasOutputData(String username, List<BufferedImage> canvases) {
        this.username = username;
        this.canvases = canvases;
    }

    public String getUsername() {
        return username;
    }

}
