package entity;

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
    public void draw(DrawingCanvas drawingCanvas) {
//        TODO: implement draw
    }
}
