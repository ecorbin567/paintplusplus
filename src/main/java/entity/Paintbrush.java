package entity;

import java.awt.*;

public class Paintbrush implements Tool{
    private float width;
    private Color colour;

    public Paintbrush(float width, Color colour) {
        this.width = width;
        this.colour = colour;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }
}
