package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MoveRecord implements Drawable{
    public final BufferedImage image;
    public final Rectangle from;
    public final Rectangle to;

    public MoveRecord(BufferedImage image, Rectangle from, Rectangle to){
        this.image = image;
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString(){
        return "Move Record: " + from + " to " + to;
    }
}
