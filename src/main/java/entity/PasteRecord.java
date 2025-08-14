package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * History record for a committed paste operation: holds the bitmap to draw and
 * the destination {@code bounds} (in canvas coordinates). Used by the renderer
 * and {@link ActionHistory} to replay and undo/redo pasted content.
 */
public class PasteRecord implements Drawable {
    public final BufferedImage image;
    public final Rectangle bounds;

    public PasteRecord(BufferedImage image, Rectangle where) {
        this.image = image;
        this.bounds = where;
    }

    @Override
    public String toString() {
        return "Paste Record: " + bounds.toString();
    }
}
