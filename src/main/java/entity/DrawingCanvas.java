package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DrawingCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private double scale = 1.0;
    private Image currentImage;
    private String selectedTool;
    private final Paintbrush paintbrush;
    private final Eraser eraser;
    private final Color backgroundColor;
    private final SelectionTool selectionTool;
    private final ActionHistory actionHistory = new ActionHistory();
    private final List<Image> importedImages = new ArrayList<>();
    private BufferedImage selectionImage;
    private Rectangle selectionBounds;
    private boolean hasSelection = false;
    private boolean draggingSelection = false;
    private boolean isDrawing = false;
    private Point dragAnchor;
    private BufferedImage canvas;
    private Point lastPoint;
    private Rectangle originalSelectionBounds;

    public DrawingCanvas() {
        setBackground(Color.WHITE);
        this.backgroundColor = Color.WHITE;
        this.paintbrush = new Paintbrush(3f, Color.BLACK);
        this.eraser = new Eraser(3f);
        this.selectionTool = new SelectionTool();
        addMouseListener(this);
        addMouseMotionListener(this);
        this.selectionBounds = null;
        this.setPreferredSize(new Dimension(800, 500));

        canvas = new BufferedImage(
                getPreferredSize().width,
                getPreferredSize().height,
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D cg = canvas.createGraphics();
        cg.setColor(backgroundColor);
        cg.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        cg.dispose();
    }

    public double getScale() {
        return this.scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // handle the resizing of canvas
        Graphics2D g2 = (Graphics2D) g.create();
        g2.scale(this.scale, this.scale);

        g2.drawImage(canvas,0,0, null);

//        for (Image image : importedImages) {
//            image.draw(g2);
//        }
//        //
//        if (!(actionHistory.getUndoStack().isEmpty())) {
//            for (Drawable d : actionHistory.getUndoStack()) {
//                if (d instanceof StrokeRecord s) {
//                    g2.setColor(s.colour);
//                    g2.setStroke(new BasicStroke(s.width,
//                            BasicStroke.CAP_ROUND,
//                            BasicStroke.JOIN_ROUND));
//                    for (int i = 1; i < s.pts.size(); i++) {
//                        Point p1 = s.pts.get(i - 1);
//                        Point p2 = s.pts.get(i);
//                        g2.drawLine(p1.x, p1.y, p2.x, p2.y);
//                    }
//                }
//            }
//        }

        // render the moving selected region
        if (hasSelection) {
            g2.drawImage(
                    selectionImage,
                    selectionBounds.x, selectionBounds.y,
                    selectionBounds.width, selectionBounds.height,
                    null
            );
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(
                    selectionBounds.x, selectionBounds.y,
                    selectionBounds.width-1, selectionBounds.height-1
            );
        }

        else if ("Selection".equals(selectedTool) && isDrawing){
            Rectangle r = selectionTool.getBounds();
            if (r.width>0 && r.height>0){
                g2.setColor(Color.DARK_GRAY);
                g2.setStroke(new BasicStroke(1));
                g2.drawRect(r.x, r.y, r.width-1, r.height-1);
            }
        }
        g2.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ("Selection".equals(selectedTool)){
            Point p = e.getPoint();
            // 1) if click inside an existing selection, start dragging as u needed
            if (hasSelection && selectionBounds.contains(p)){
                draggingSelection = true;
                dragAnchor = new Point(p.x - selectionBounds.x,
                                        p.y - selectionBounds.y);
            } else if (!hasSelection){ // 2) clicked outside existing selection
                // commit it permanently back into the canvas
                if (originalSelectionBounds != null) {
                    Graphics2D cg = canvas.createGraphics();
                    cg.setColor(backgroundColor);
                    cg.fillRect(
                            originalSelectionBounds.x, originalSelectionBounds.y,
                            originalSelectionBounds.width, originalSelectionBounds.height
                    );
                    cg.drawImage(selectionImage,
                            selectionBounds.x, selectionBounds.y,
                            selectionBounds.width, selectionBounds.height, null);
                    cg.dispose();

                }

                hasSelection = false;
                selectionImage = null;
                selectionBounds = null;
                originalSelectionBounds = null;
                draggingSelection = false;
                selectionTool.cancel();
            } else { // 3) otherwise, no selection at all, start drawing new selection rectangle
                selectionTool.start(p);
                isDrawing = true;
            }

            repaint();
            return;
        }

        if (SwingUtilities.isLeftMouseButton(e)){
            Color c = Objects.equals(this.selectedTool, "Eraser") ? backgroundColor
                    : paintbrush.getColour();
            float w = Objects.equals(this.selectedTool, "Eraser") ? eraser.getWidth()
                    : paintbrush.getWidth();

            StrokeRecord currentStroke = new StrokeRecord(c, w);
            currentStroke.pts.add(e.getPoint());
            actionHistory.push(currentStroke);
            lastPoint = e.getPoint();
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if ("Selection".equals(selectedTool)){
            Point p = e.getPoint();
            if (draggingSelection){
                // translate the live selection
                selectionBounds.x = p.x - dragAnchor.x;
                selectionBounds.y = p.y - dragAnchor.y;
            } else {
                // update live marquee
                selectionTool.drag(p);
            }
            repaint();
            return;
        }

        Point p = e.getPoint();
        Drawable curr = actionHistory.getCurrentState();

        if (curr instanceof StrokeRecord strokeRecord) {
            strokeRecord.pts.add(p);
            actionHistory.setCurrentState(strokeRecord);
        }


        Graphics2D cg = canvas.createGraphics();
        if ("Eraser".equals(selectedTool)) {
            cg.setColor(backgroundColor);
            cg.setStroke(new BasicStroke(
                    eraser.getWidth(),
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));
        } else {
            cg.setColor(paintbrush.getColour());
            cg.setStroke(new BasicStroke(
                    paintbrush.getWidth(),
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));
        }

        cg.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
        cg.dispose();

        lastPoint = p;
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        actionHistory.setCurrentState(null);         // finished; ready for a fresh stroke
        if ("Selection".equals(selectedTool)) {
            Point p = e.getPoint();
            Graphics2D g = canvas.createGraphics();
            if (!draggingSelection) {
                // user finished selecting the marked area (inside the rectangle)
                selectionTool.finish(p);
                Rectangle r = selectionTool.getBounds();
                if (r.width > 0 && r.height > 0) {
                    // try to get the state of the stack, whenever you mouse release,
                    // create new instance on the stack for easy undo and redo functionality directly built in
                    // somehow use drawable to only capture state from the strokerecord/actionhistory,
                    // and capture those brushstrokes/image, etc.
                    // TODO: make selection tool a drawable I guess to make easy work of stack implementation of undo and redo
                    // extract
                    selectionImage = canvas.getSubimage(r.x, r.y, r.width, r.height);
                    selectionBounds = new Rectangle(r);
                    hasSelection = true;

                    g.setColor(backgroundColor);
                    g.fillRect(r.x, r.y, r.width, r.height);

                }
            } else {
                if (hasSelection && selectionImage != null) {
                    g.drawImage(
                            selectionImage,
                            selectionBounds.x, selectionBounds.y,
                            selectionBounds.width, selectionBounds.height,
                            null
                    );
                }
                hasSelection = false;
                selectionImage = null;
                selectionBounds= null;
            }
                g.dispose();
                // reset dragging selection here
                draggingSelection = false;
                isDrawing = false;
                selectionTool.cancel();
                repaint();

        }
    }

    public void erase() {
        this.selectedTool = "Eraser";
    }

    public void paint() {
        this.selectedTool = "Paintbrush";
    }

    public void undo() {
//        Drawable prevState = actionHistory.undo();
//        importedImages.clear();
//
//        if (prevState instanceof Image image) {
//            importedImages.add(image);
//            setCurrentImage(image);
//        }
//        repaint();
        actionHistory.undo();
        rebuildCanvasFromHistory();
       }

    public void redo() {
//        Drawable nextState = actionHistory.redo();
//        importedImages.clear();
//
//        if (nextState instanceof Image image) {
//            importedImages.add(image);
//            setCurrentImage(image);
//        }
//        repaint();
        actionHistory.redo();
        rebuildCanvasFromHistory();
    }
    private void rebuildCanvasFromHistory(){
        // clear background
        Graphics2D g2 = canvas.createGraphics();
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // 2) replay every StrokeRecord in the undo stack
        for (Drawable d : actionHistory.getUndoStack()) {
            if (d instanceof StrokeRecord s) {
                g2.setColor(s.colour);
                g2.setStroke(new BasicStroke(
                        s.width,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND));
                for (int i = 1; i < s.pts.size(); i++) {
                    Point p1 = s.pts.get(i-1);
                    Point p2 = s.pts.get(i);
                    g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                }
            }
        }
        g2.dispose();
        repaint();
    }

    public Paintbrush getPaintbrush(){
        return this.paintbrush;
    }
    public void setSelectedTool(String toolname){
        this.selectedTool = toolname;
    }
    public String getSelectedTool(){
        return this.selectedTool;
    }
    public SelectionTool getSelectionTool(){
        return this.selectionTool;
    }
  
    public BufferedImage getImage() {
        //Used for Saving Image as PNG File
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        this.paint(g2d);
        g2d.dispose();
        return image;
    }

    public void setPaintBrushSize(float size) {
        this.paintbrush.setWidth(size);
    }

    public void setPaintBrushColor(Color color){
        this.paintbrush.setColour(color);
    }

    public void setEraserSize(float size) {
        this.eraser.setWidth(size);
    }

    public void setCurrentImage(Image image) {
        this.currentImage = image;
    }

    public Image getCurrentImage() {
        return this.currentImage;
    }

    public void addDrawableElement(Image importedImage) {
//        importedImages.add(importedImage);
//        setCurrentImage(importedImage);
//        repaint();
        Graphics2D cg = canvas.createGraphics();
        importedImage.draw(cg);
        repaint();
    }

    public void resizeLastImportedImage(int newWidth, int newHeight) {
        if (!importedImages.isEmpty()) {
            Image lastImage = importedImages.get(importedImages.size() - 1);
            actionHistory.push(lastImage.clone());
            lastImage.resize(newWidth, newHeight);
        }
    }

    public void rotateLastImportedImage(double degrees) {
        if (!importedImages.isEmpty()) {
            Image lastImage = importedImages.get(importedImages.size() - 1);
            actionHistory.push(lastImage.clone());
            lastImage.rotate(degrees);
            repaint();
        }
    }

    public ActionHistory getActionHistory() {
        return this.actionHistory;
    }

    private static class Pair<A,B> {
        final A first;
        final B second;
        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    // We don't need these, but must include them:
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
}
