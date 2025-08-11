package use_case.newcanvas;

import java.awt.image.BufferedImage;

/**
 * The Input Data for the New Canvas Use Case.
 */
public class NewCanvasInputData {

    private final String username;
    private final String password;
    private final BufferedImage importedCanvas;

    public NewCanvasInputData(String username, String password, BufferedImage importedCanvas) {
        this.username = username;
        this.password = password;
        this.importedCanvas = importedCanvas;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public BufferedImage getImportedCanvas() {
        return importedCanvas;
    }
}
