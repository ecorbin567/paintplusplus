package entity;

public class Paintbrush implements DrawableElement{
    private double width;
    private String colour;

    public Paintbrush(double width, String colour) {
        this.width = width;
        this.colour = colour;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public void draw(DrawingCanvas drawingCanvas) {
//        TODO: implement draw
    }
}
