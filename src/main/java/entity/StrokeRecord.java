package entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/** One continuous stroke of a paintbrush or eraser */
public class StrokeRecord implements Drawable {
    public final List<Point> pts = new ArrayList<>();
    public final Color colour;
    public final float width;

    public StrokeRecord(Color colour, float width) {
        this.colour = colour;
        this.width  = width;
    }

    @Override
    public String toString(){
        return String.format(
                "Stroke color: RGB(%d, %d, %d), size: %.2f",
                colour.getRed(), colour.getGreen(), colour.getBlue(),
                width);
    }
}
