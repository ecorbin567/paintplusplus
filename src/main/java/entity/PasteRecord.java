package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PasteRecord implements Drawable {
    private final BufferedImage image;
    private final Rectangle bounds;

    public PasteRecord(BufferedImage image, Rectangle where) {
        this.image = image;
        this.bounds = where;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public String toString() {
        return "Paste Record: " + bounds.toString();
    }
}
