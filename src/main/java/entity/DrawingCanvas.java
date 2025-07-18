package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Objects;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private String selectedTool;

    private final Paintbrush paintbrush;
    private final Eraser eraser;
    private final Color backgroundColor;
    private final ActionHistory actionHistory = new ActionHistory();

    public DrawingCanvas() {
        setBackground(Color.WHITE);
        this.backgroundColor = Color.WHITE;
        this.paintbrush = new Paintbrush(3f, Color.BLACK);
        this.eraser = new Eraser(3f);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!(actionHistory.getUndoStack().isEmpty())) {
            for (Drawable d : actionHistory.getUndoStack()) {
                if (d instanceof StrokeRecord s) {
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
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            Color c = Objects.equals(this.selectedTool, "Eraser") ? backgroundColor
                    : paintbrush.getColour();
            float w = Objects.equals(this.selectedTool, "Eraser") ? eraser.getWidth()
                    : paintbrush.getWidth();

            StrokeRecord currentStroke = new StrokeRecord(c, w);
            currentStroke.pts.add(e.getPoint());
            actionHistory.push(currentStroke);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Drawable curr = actionHistory.getCurrentState();
        if (curr != null) {
            if (curr instanceof StrokeRecord strokeRecord) {
                strokeRecord.pts.add(e.getPoint());
                actionHistory.setCurrentState(strokeRecord);
            }
            repaint();                 // ask Swing to invoke paintComponent()
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        actionHistory.setCurrentState(null);         // finished; ready for a fresh stroke
    }

    public void erase() {
        this.selectedTool = "Eraser";
    }

    public void paint() {
        this.selectedTool = "Paintbrush";
    }

    public void undo() {
        actionHistory.undo();
        repaint();
    }

    public void redo() {
        actionHistory.redo();
        repaint();
    }

    // We don't need these, but must include them:
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
