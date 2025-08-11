package entity;

import java.awt.*;

public class SelectionTool implements Drawable {

    private Point startPoint; // start point of the selection on the canvas
    private Point currentPoint; // end point of the selection on the canvas

    public SelectionTool() {
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