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

/**
 * A canvas to paint and edit images on.
 */
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
    private boolean hasCutOut = false;
    private boolean isDrawing = false;
    private Point dragAnchor;
    private List<Rectangle> clearRegions = new ArrayList<>();
    private final List<Pair<BufferedImage, Rectangle>> commitedSelections = new ArrayList<>();

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

        for (Image image : importedImages) {
            image.draw(g2);
        }
        //
        if (!(actionHistory.getUndoStack().isEmpty())) {
            for (Editable d : actionHistory.getUndoStack()) {
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

        g2.setColor(backgroundColor);
        for (Rectangle hole: clearRegions){
            g2.fillRect(hole.x, hole.y, hole.width, hole.height);
        }

        // always render all active selections
        for (var p: commitedSelections){
            g2.drawImage(p.first, p.second.x, p.second.y,
                    p.second.width, p.second.height, null);
        }
        // render the moving selected region
        if (hasSelection && selectionImage != null && selectionBounds != null) {
            g2.drawImage(
                    selectionImage,
                    selectionBounds.x, selectionBounds.y,
                    selectionBounds.width, selectionBounds.height,
                    null
            );
        }
        // selection tool rendering logic
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(1));
        if ("Selection".equals(selectedTool) && isDrawing){
            Rectangle r = selectionTool.getBounds();
            if (r.width>0 && r.height>0){
                g2.drawRect(r.x,r.y,r.width-1, r.height-1);
            }
        } else if (hasSelection && selectionBounds != null){
            g2.setStroke(new BasicStroke(1));
            g2.drawRect(selectionBounds.x, selectionBounds.y,
                    selectionBounds.width-1, selectionBounds.height-1);
        }
        g2.dispose();
    }

    private void commitCut(){
        // grabs full canvas snapshot
        BufferedImage full = getImage();
        Rectangle r = selectionBounds;
        selectionImage = full.getSubimage(r.x, r.y, r.width, r.height);
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
            } else if (hasSelection){ // 2) clicked outside existing selection
                // commit current image at its last bounds
                draggingSelection = false;
                hasCutOut = false;

                // get rid of old selection if one existed
                commitedSelections.add(
                        new Pair<>(selectionImage,
                                new Rectangle(selectionBounds))
                );

                clearRegions.add(new Rectangle(selectionBounds));
                // clear out active selection state
                hasSelection = false;
                selectionImage = null;
                selectionBounds = null;
                selectionTool.cancel();
                isDrawing = false;
            } else { // 3) otherwise, no selection at all, start drawing new selection rectangle
                draggingSelection = false;
                selectionTool.start(p);
                isDrawing = true;
            }

            repaint();
            return;
        }

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
        if ("Selection".equals(selectedTool)){
            Point p = e.getPoint();
            if (draggingSelection){
                // on the first drag, blank out the original rectangle
                if (!hasCutOut && selectionBounds != null){
                    clearRegions.add(new Rectangle(selectionBounds));
                    hasCutOut = true;
                }
                // now move it
                selectionBounds.x = p.x - dragAnchor.x;
                selectionBounds.y = p.y - dragAnchor.y;
            } else {
                // update live marquee
                selectionTool.drag(p);
            }
            repaint();
            return;
        }
        Editable curr = actionHistory.getCurrentState();
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
        if ("Selection".equals(selectedTool)){
            Point p = e.getPoint();
            if (!draggingSelection){
                // user finished selecting the marked area (inside the rectangle)
                selectionTool.finish(p);
                isDrawing = false;
                Rectangle r = selectionTool.getBounds();
                if (r.width>0 && r.height>0){
                    BufferedImage full = getImage(); // TODO: fix subimage implementation, capture maybe only drawables?
                    // try to get the state of the stack, whenever you mouse release,
                    // create new instance on the stack for easy undo and redo functionality directly built in
                    // somehow use drawable to only capture state from the strokerecord/actionhistory,
                    // and capture those brushstrokes/image, etc.
                    // TODO: make selection tool a drawable I guess to make easy work of stack implementation of undo and redo

                    selectionImage = full.getSubimage(r.x, r.y, r.width, r.height);
                    selectionBounds = new Rectangle(r);
                    hasSelection = true;
                }
            }
            // reset dragging selection here
            draggingSelection = false;
            selectionTool.cancel();
            isDrawing = false;
            repaint();
            actionHistory.push(getSelectionTool()); //fix this later, how to push that state onto actionhistory stack
        }
    }

    public void erase() {
        this.selectedTool = "Eraser";
    }

    public void paint() {
        this.selectedTool = "Paintbrush";
    }

    public void undo() {
        Editable prevState = actionHistory.undo();
        importedImages.clear();

        if (prevState instanceof Image image) {
            importedImages.add(image);
            setCurrentImage(image);
        }
        repaint();
    }

    public void redo() {
        Editable nextState = actionHistory.redo();
        importedImages.clear();

        if (nextState instanceof Image image) {
            importedImages.add(image);
            setCurrentImage(image);
        }
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
        importedImages.add(importedImage);
        setCurrentImage(importedImage);
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
