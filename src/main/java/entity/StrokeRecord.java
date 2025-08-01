package entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/** One continuous stroke of a paintbrush or eraser */
public class StrokeRecord implements Editable {
    public final List<Point> pts = new ArrayList<>();
    public final Color colour;
    public final float width;

    StrokeRecord(Color colour, float width) {
        this.colour = colour;
        this.width  = width;
    }
}
