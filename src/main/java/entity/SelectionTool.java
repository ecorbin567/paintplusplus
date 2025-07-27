package entity;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

public class SelectionTool{

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
        startPoint = currentPoint = null;
    }
    public void render(Graphics2D g){
        if (startPoint == null || currentPoint == null) return;
        int x = Math.min(startPoint.x, currentPoint.x);
        int y = Math.min(startPoint.y, currentPoint.y);
        int w = Math.abs(startPoint.x - currentPoint.x);
        int h = Math.abs(startPoint.y - currentPoint.y);
        g.setColor(Color.lightGray);
        g.drawRect(x,y,w,h);

    }
//        addMouseListener(new MouseAdapter() {
//            @Override
//             public void mousePressed(MouseEvent e) {
//                startPoint = e.getPoint();
//                currentPoint = startPoint;
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                currentPoint = e.getPoint();
//                // below not sure if needs change, but remove temp drawing box when mouse released
//                startPoint = null;
//                currentPoint = null;
//                repaint();
//            }
//        });
//
//        addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                currentPoint = e.getPoint();
//                repaint();
//            }
//        });
//    }
//    @Override
//    protected void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//        if (startPoint!=null && currentPoint!=null) {
//            int x = Math.min(startPoint.x, currentPoint.x);
//            int y = Math.min(startPoint.y, currentPoint.y);
//            int width = Math.abs(startPoint.x - currentPoint.x);
//            int height = Math.abs(startPoint.y - currentPoint.y);
//
//            g.setColor(Color.lightGray);
//            g.drawRect(x, y, width, height);
//
//        }
//    }

}
