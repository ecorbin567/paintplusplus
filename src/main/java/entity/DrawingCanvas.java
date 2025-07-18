package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private Paintbrush paintbrush;
    private Eraser eraser;
    private SelectionTool selectionTool;
    private Color backgroundColor;
    private final ArrayList<StrokeRecord> strokes = new ArrayList<>();
    private StrokeRecord currentStroke;

    public DrawingCanvas() {
        setBackground(Color.WHITE);
        this.backgroundColor = Color.WHITE;
        this.paintbrush = new Paintbrush(3f, Color.BLACK);
        this.eraser = new Eraser(3f, false);
        this.selectionTool = new SelectionTool(); // not sure if selection tool belongs here now
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (StrokeRecord s : strokes) {
            g2.setColor(s.colour);
            g2.setStroke(new BasicStroke(s.width,
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));
            for (int i = 1; i < s.pts.size(); i++) {
                Point p1 = s.pts.get(i - 1);
                Point p2 = s.pts.get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Color c = eraser.isErasing() ? backgroundColor
                    : paintbrush.getColour();
            float w = eraser.isErasing() ? eraser.getWidth()
                    : paintbrush.getWidth();

            currentStroke = new StrokeRecord(c, w);
            currentStroke.pts.add(e.getPoint());
            strokes.add(currentStroke);
        }
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentStroke != null) {
            currentStroke.pts.add(e.getPoint());
            repaint();                 // ask Swing to invoke paintComponent()
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        currentStroke = null;          // finished; ready for a fresh stroke
    }

    public void erase() {
        this.eraser.setErasing(true);
    }

    public void paint() {
        this.eraser.setErasing(false);
    }

    public int[] getDrawingCanvasDimensions(){
        int width = getWidth();
        int height = getHeight();

    }

    // We don't need these, but must include them:
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
