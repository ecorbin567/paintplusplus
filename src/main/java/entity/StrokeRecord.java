package entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/** Everything needed to redraw one continuous stroke. */
public class StrokeRecord implements Drawable{
    public final List<Point> pts = new ArrayList<>();
    public final Color colour;
    public final float width;

    public StrokeRecord(Color colour, float width) {
        this.colour = colour;
        this.width  = width;
    }
}
