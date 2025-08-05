package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Output Data for the New Canvas Use Case.
 */
public class NewCanvasOutputData {

    private final String username;

    public NewCanvasOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
