package entity;

import java.awt.*;
import java.util.Stack;

public class CanvasState {
    Stack<Drawable> actions;
    private double scale = 1.0;

    public double getScale() {
        return this.scale;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }

    /**
     * Returns canvas representation as an image.
     *
     * @return an Image object.
     */
    public Image renderCanvas() {
        // TODO: implement
        return null;
    }
}
