package entity;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * One continuous stroke of a paintbrush or eraser.
 */
public class StrokeRecord implements Drawable {
    private final List<Point> pts = new ArrayList<>();
    private final Color colour;
    private final float width;

    public StrokeRecord(Color colour, float width) {
        this.colour = colour;
        this.width = width;
    }

    public List<Point> getPts() {
        return pts;
    }

    public Color getColour() {
        return colour;
    }

    public float getWidth() {
        return width;
    }
}