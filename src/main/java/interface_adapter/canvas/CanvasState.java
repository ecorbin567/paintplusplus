package interface_adapter.canvas;

import java.awt.image.BufferedImage;

/**
 * The State information representing a canvas.
 */
public class CanvasState {
    private BufferedImage initialImportedImage; // canvas select screen

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CanvasState() {

    }

    public BufferedImage getInitialImportedImage() {
        return initialImportedImage;
    }

    public void setInitialImportedImage(BufferedImage initialImportedImage) {
        this.initialImportedImage = initialImportedImage;
    }
}
