package entity;

import java.awt.*;

public class Eraser implements DrawableElement{

    private float width;
    private boolean isErasing;

    public Eraser(float width, boolean isErasing) {
        this.width = width;
        this.isErasing = isErasing;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isErasing() {
        return isErasing;
    }

    public void setErasing(boolean erasing) {
        this.isErasing = erasing;
    }

    @Override
    public Image renderAsImage() {
//        TODO: implement render
    }
}
