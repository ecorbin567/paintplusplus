package entity;

import java.awt.Rectangle;

/**
 * Immutable history record that represents a "cut" (cleared/whited-out) rectangular region
 * on the canvas; used by rendering to fill the area and by {@link ActionHistory} to
 * support undo/redo of selection drags that temporarily remove pixels.
 */
public class CutRecord implements Drawable {
    public final Rectangle bounds;

    public CutRecord(Rectangle b) {
        this.bounds = b;
    }

    @Override
    public String toString() {
        return "CutRecord [bounds=" + bounds + "]";
    }
}
