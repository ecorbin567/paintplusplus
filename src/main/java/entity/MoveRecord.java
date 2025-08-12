package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public record MoveRecord(BufferedImage image, Rectangle from, Rectangle to) implements Drawable {
    @Override
    public String toString() {
        return "Move Record: " + from + " to " + to;
    }
}
