package entity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class SelectionTool implements Drawable{

    private Point startPoint; // start point of the selection on the canvas
    private Point currentPoint; // end point of the selection on the canvas

    public SelectionTool() {}

    public void start(Point p){
        startPoint = p;
        currentPoint = p;
    }
    public void drag(Point p){
        currentPoint = p;
    }
    public void finish(Point p){
        currentPoint = p;
    }
    public void cancel(){
        startPoint = null;
        currentPoint = null;
    }
    public Rectangle getBounds() {
        if (startPoint == null || currentPoint == null) {
            return new Rectangle();
        }
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int w = Math.abs(startPoint.x - currentPoint.x);
        int h = Math.abs(startPoint.y - currentPoint.y);
        return new Rectangle(x, y, w, h);
    }
    public void render(Graphics2D g){
        Rectangle r = getBounds();
        if (r.width > 0 && r.height > 0){
            // save old stroke
            Stroke old = g.getStroke();
            //force teh 1px stroke
            g.setStroke(new BasicStroke(1f));
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(r.x, r.y, r.width, r.height);

            g.setStroke(old);
        }
    }

}
