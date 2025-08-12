package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PasteRecord implements Drawable{
    public final BufferedImage image;
    public final Rectangle bounds;

    public PasteRecord(BufferedImage image, Rectangle where){
        this.image = image;
        this.bounds = where;
    }
    @Override
    public String toString(){
        return "Paste Record: " + bounds.toString();
    }
}
