package entity;

import java.awt.*;

/**
 * Tracks the user's rectangular marquee during a selection interaction.
 * <
 * This tool records the press point and the latest mouse point (in canvas
 * coordinates) and provides a normalized {@link Rectangle} representing the
 * current selection bounds. It contains no rendering logic and is typically
 * driven by interactors/controllers while the view renders based on the
 * produced bounds. Although it implements {@link Drawable}, it is a transient
 * tool helper and is not meant to be persisted to history.
 */
public class SelectionTool implements Drawable {

    private Point startPoint; // start point of the selection on the canvas
    private Point currentPoint; // end point of the selection on the canvas

    public SelectionTool() {
        //Don't Need To Set Points
    }

    public void start(Point p) {
        startPoint = p;
        currentPoint = p;
    }

    public void drag(Point p) {
        currentPoint = p;
    }

    public void finish(Point p) {
        currentPoint = p;
    }

    public void cancel() {
        startPoint = null;
        currentPoint = null;
    }

    public Rectangle getBounds() {
        if (startPoint == null || currentPoint == null) {
            return new Rectangle();
        }
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int w = Math.abs(startPoint.x - currentPoint.x);
        int h = Math.abs(startPoint.y - currentPoint.y);
        return new Rectangle(x, y, w, h);
    }
}