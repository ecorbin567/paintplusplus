package entity;

import java.awt.*;
import java.util.*;
import java.util.List;

/** Everything needed to redraw one continuous stroke. */
public class StrokeRecord {
    final List<Point> pts = new ArrayList<>();
    final Color colour;
    final float width;

    StrokeRecord(Color colour, float width) {
        this.colour = colour;
        this.width  = width;
    }
}
