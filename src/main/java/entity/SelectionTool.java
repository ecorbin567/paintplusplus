package entity;

import java.awt.*;

public class SelectionTool implements Drawable {

    private Point startPoint;
    private Point currentPoint;

    public SelectionTool() {
    }

    /**
     * Start the selection.
     * @param p The point at which the selection starts.
     */
    public void start(Point p) {
        startPoint = p;
        currentPoint = p;
    }

    /**
     * Drag the selection box.
     * @param p The current point on the screen.
     */
    public void drag(Point p) {
        currentPoint = p;
    }

    /**
     * Finish dragging the selection box.
     * @param p The current point on the screen.
     */
    public void finish(Point p) {
        currentPoint = p;
    }

    /**
     * Cancel selection.
     */
    public void cancel() {
        startPoint = null;
        currentPoint = null;
    }

    /**
     * Get the bounds of the selection box.
     * @return Rectangle the bounds of the selection box
     */
    public Rectangle getBounds() {
        if (startPoint == null || currentPoint == null) {
            return new Rectangle();
        }
        final int x = Math.min(startPoint.x, currentPoint.x);
        final int y = Math.min(startPoint.y, currentPoint.y);
        final int w = Math.abs(startPoint.x - currentPoint.x);
        final int h = Math.abs(startPoint.y - currentPoint.y);
        return new Rectangle(x, y, w, h);
    }
}
