package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Immutable history record representing a committed move of a selected bitmap:
 * stores the pixel data and its source ({@code from}) and destination ({@code to})
 * rectangles in canvas coordinates; used by rendering and {@link ActionHistory}
 * to support undo/redo of selection moves.
 */
public record MoveRecord(BufferedImage image, Rectangle from, Rectangle to) implements Drawable {
    @Override
    public String toString() {
        return "Move Record: " + from + " to " + to;
    }
}
