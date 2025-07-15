package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<Point> points = new ArrayList<>();

    public DrawingCanvas() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            if (p1 != null && p2 != null) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        points.add(e.getPoint());  // start a new stroke
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        points.add(e.getPoint());  // keep adding points as we drag
        repaint();
    }

    // We don't need these, but must include them:
    @Override public void mouseReleased(MouseEvent e) { points.add(null); }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
