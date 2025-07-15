package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private ArrayList<Point> points = new ArrayList<>();
    private Paintbrush paintbrush;
    private Eraser eraser;
    private Color backgroundColor;
    public DrawingCanvas() {
        setBackground(Color.WHITE);
        this.backgroundColor = Color.WHITE;
        this.paintbrush = new Paintbrush(3f, Color.BLACK);
        this.eraser = new Eraser(3f, false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        if (this.eraser.isErasing()) {
            float width = this.eraser.getWidth();
            BasicStroke stroke = new BasicStroke(width);
            g2d.setStroke(stroke);
            g2d.setColor(backgroundColor);
        }
        else {
            float width = this.paintbrush.getWidth();
            BasicStroke stroke = new BasicStroke(width);
            g2d.setStroke(stroke);
            g2d.setColor(this.paintbrush.getColour());
        }
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            if (p1 != null && p2 != null) {
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
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

    public void erase() {
        this.eraser.setErasing(true);
    }

    public void paint() {
        this.eraser.setErasing(false);
    }

    // We don't need these, but must include them:
    @Override public void mouseReleased(MouseEvent e) { points.add(null); }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
