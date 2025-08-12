package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MoveRecord implements Drawable {
    private final BufferedImage image;
    private final Rectangle from;
    private final Rectangle to;

    public MoveRecord(BufferedImage image, Rectangle from, Rectangle to) {
        this.image = image;
        this.from = from;
        this.to = to;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Rectangle getFrom() {
        return from;
    }

    public Rectangle getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Move Record: " + from + " to " + to;
    }
}
