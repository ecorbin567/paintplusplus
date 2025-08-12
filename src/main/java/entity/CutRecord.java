package entity;

import java.awt.Rectangle;

public class CutRecord implements Drawable {
    private final Rectangle bounds;

    public CutRecord(Rectangle rect) {

        this.bounds = rect;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public String toString() {

        return "CutRecord [bounds=" + bounds + "]";
    }
}
