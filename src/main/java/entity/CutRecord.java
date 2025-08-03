package entity;

import java.awt.Rectangle;

public class CutRecord implements Drawable{
    public final Rectangle bounds;
    public CutRecord(Rectangle b){
        this.bounds = b;
    }
}
