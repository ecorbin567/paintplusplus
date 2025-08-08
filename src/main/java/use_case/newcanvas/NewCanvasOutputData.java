package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;

/**
 * Output Data for the New Canvas Use Case.
 */
public class NewCanvasOutputData {

    private final String username;
    private BufferedImage importedCanvas;

    public NewCanvasOutputData(String username,  BufferedImage importedCanvas) {
        this.username = username;
        this.importedCanvas = importedCanvas;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Get the initially imported canvas
     * @return Canvas to import, possibly null
     */
    public BufferedImage getImportedCanvas() {
        return importedCanvas;
    }

    public void setImportedCanvas(BufferedImage canvasToImport) {
        this.importedCanvas = canvasToImport;
    }

}
