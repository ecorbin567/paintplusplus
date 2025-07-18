package entity;

import java.awt.*;

public class Eraser implements Tool{

    private float width;
    private Color color;

    public Eraser(float width, Color color) {
        this.width = width;
        this.color = color;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColour(Color colour) {
        this.color = colour;
    }
}
