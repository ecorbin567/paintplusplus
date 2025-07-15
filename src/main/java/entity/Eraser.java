package entity;

import java.awt.*;

public class Eraser implements DrawableElement{

    private double width;

    public Eraser(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public Image renderAsImage() {
//        TODO: implement render
    }
}
