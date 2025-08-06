package entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
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
    private Rectangle selectionOriginalBounds;
    private boolean hasSelection = false;
    private boolean draggingSelection = false;
    private boolean hasCutOut = false;
    private boolean isDrawing = false;
    private Point dragAnchor;
    private List<Rectangle> clearRegions = new ArrayList<>();
    private final List<Pair<BufferedImage, Rectangle>> commitedSelections = new ArrayList<>();
    // dotted selection box border effect fields
    private final float[] ANTS_DASH = {4f, 4f}; // dash, gap in px scaled with the canvas
    private float antsPhase = 0f;
    private final javax.swing.Timer antsTimer = new javax.swing.Timer(60, e -> {
        antsPhase = (antsPhase +1f) % (ANTS_DASH[0] + ANTS_DASH[1]);
        repaint();
    });

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

    private void updateAntsTimer(){
        boolean shouldRun = "Selection".equals(selectedTool)&&(isDrawing||hasSelection);
        if (shouldRun&&!antsTimer.isRunning()) antsTimer.start();
        if (!shouldRun&&antsTimer.isRunning()) antsTimer.stop();
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
            for (Drawable d : actionHistory.getUndoStack()) {
                // this logic is refractored into drawDrawable() below paintComponent
                drawDrawable(g2, d);
            }
        }

        Drawable head = actionHistory.getCurrentState();
        if (head != null){
            drawDrawable(g2, head);
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
        Rectangle r = null;

        if ("Selection".equals(selectedTool) && isDrawing){
            r = selectionTool.getBounds();
        } else if (hasSelection && selectionBounds != null){
            r = selectionBounds;
        }
        if (r != null && r.width>0 && r.height>0){
            Stroke oldStroke = g2.getStroke();
            Color oldColor = g2.getColor();
            // dark dashes
            Stroke dark = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10f, ANTS_DASH, antsPhase);
            g2.setStroke(dark);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRect(r.x, r.y, r.width - 1, r.height - 1);

            // light dashes shifted by one dash length to fill the gaps
            Stroke light = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10f, ANTS_DASH, antsPhase + ANTS_DASH[0]);
            g2.setStroke(light);
            g2.setColor(new Color(230, 230, 230)); // very light gray
            g2.drawRect(r.x, r.y, r.width - 1, r.height - 1);

            g2.setStroke(oldStroke);
            g2.setColor(oldColor);
        }
    }

    private void drawDrawable(Graphics2D g2, Drawable d) {
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
        } else if (d instanceof PasteRecord pr) {
            g2.drawImage(pr.image,
                    pr.bounds.x, pr.bounds.y,
                    pr.bounds.width, pr.bounds.height,
                    null);
        } else if (d instanceof CutRecord cr) {
            g2.setColor(backgroundColor);
            g2.fillRect(cr.bounds.x, cr.bounds.y,
                    cr.bounds.width, cr.bounds.height);

        } else if (d instanceof MoveRecord mr) {
            /* blank the old rectangle */
            g2.setColor(backgroundColor);
            g2.fillRect(mr.from.x, mr.from.y, mr.from.width, mr.from.height);

            /* draw the bitmap at its new spot */
            g2.drawImage(mr.image,
                    mr.to.x, mr.to.y,
                    mr.to.width, mr.to.height,
                    null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ("Selection".equals(selectedTool)){
            Point p = e.getPoint();
            // 1) if click inside an existing selection, start dragging as u needed
            if (hasSelection && selectionBounds.contains(p)){
                draggingSelection = true;
                hasCutOut = false;
                dragAnchor = new Point(p.x - selectionBounds.x,
                                        p.y - selectionBounds.y);
            } else if (hasSelection){ // 2) clicked outside existing selection
                // commit current image at its last bounds
                draggingSelection = false;
                hasCutOut = false;

                // Remove the temporary CutRecord if it’s still the head
                if (actionHistory.getCurrentState() instanceof CutRecord) {
                    actionHistory.undo();              // pops the CutRecord
                }
                // push one MoveRecord so that it reps the whole operation
                Rectangle dest = new Rectangle(selectionBounds);
                Rectangle src = new Rectangle(selectionOriginalBounds);
                actionHistory.push(new MoveRecord(selectionImage, src, dest));

                commitedSelections.add(new Pair<>(selectionImage, dest));
                clearRegions.add(src);

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
            updateAntsTimer();
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
                if (!hasCutOut){
                    Rectangle hole = new Rectangle(selectionBounds);

                    clearRegions.add(hole);
                    actionHistory.push(new CutRecord(hole));
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
        Drawable curr = actionHistory.getCurrentState();
        if (curr != null) {
            if (curr instanceof StrokeRecord strokeRecord) {
                strokeRecord.pts.add(e.getPoint());
//                actionHistory.setCurrentState(strokeRecord);
                // the above kept calling the undo stack pop, it discarded the most recent PasteRecord,
                // so pasted bitmaps always stay on the screen regardless of what next button we press/tool to use
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
                if (r.width>0 && r.height>0) {
                    BufferedImage full = getImage();
                    selectionImage = full.getSubimage(r.x, r.y, r.width, r.height);
                    selectionBounds = new Rectangle(r);
                    selectionOriginalBounds = new Rectangle(r);
                    hasSelection = true;


                    // apparently layering issue functionality still working, bugs need fixing but managable
                }
            }
            // reset dragging selection here
            draggingSelection = false;
            selectionTool.cancel();
            repaint();

            updateAntsTimer();
        }
    }

//    private void cutFromCommitted(Rectangle cut) { // no usages
//        for (Pair<BufferedImage, Rectangle> pair : commitedSelections) {
//            Rectangle inter = cut.intersection(pair.second);
//            if (!inter.isEmpty()) {
//                Graphics2D g = pair.first.createGraphics();
//                g.setComposite(AlphaComposite.Src);
//                g.setColor(backgroundColor);
//                // translate intersection into the pasted-image’s local coords
//                g.fillRect(inter.x - pair.second.x,
//                        inter.y - pair.second.y,
//                        inter.width, inter.height);
//                g.dispose();
//            }
//        }
//    }

    private void rebuildStateFromHistory(){
        commitedSelections.clear();
        clearRegions.clear();    // NEW

        // past states
        for (Drawable d : actionHistory.getUndoStack()) {
            addFromDrawable(d);
        }
        // current head
        addFromDrawable(actionHistory.getCurrentState());
    }

    // helper function for rebuildStateFromHistory()
    private void addFromDrawable(Drawable d) {
        if (d instanceof PasteRecord pr) {
            commitedSelections.add(
                    new Pair<>(pr.image, new Rectangle(pr.bounds)));
        } else if (d instanceof CutRecord cr) {
            clearRegions.add(new Rectangle(cr.bounds));

        } else if (d instanceof  MoveRecord mr){
            clearRegions.add(new Rectangle(mr.from));
            commitedSelections.add(
                    new Pair<>(mr.image, new Rectangle(mr.to)));
        }
    }

    public void erase() {
        this.selectedTool = "Eraser";
    }

    public void paint() {
        this.selectedTool = "Paintbrush";
    }

    public void undo() {
        Drawable prevState = actionHistory.undo();
        importedImages.clear();

        rebuildStateFromHistory(); // call the rebuildstate for selection tool to work with
        if (prevState instanceof Image image) {
            importedImages.add(image);
            setCurrentImage(image);
        }
        repaint();
        updateAntsTimer();
    }

    public void redo() {
        Drawable nextState = actionHistory.redo();

        if (nextState == null) {
            return;
        }
        importedImages.clear();
        rebuildStateFromHistory();
        if (nextState instanceof Image image) {
            importedImages.add(image);
            setCurrentImage(image);
        }
        repaint();
        updateAntsTimer();
    }

    public Paintbrush getPaintbrush(){
        return this.paintbrush;
    }
    public void setSelectedTool(String toolname){
        this.selectedTool = toolname;
        updateAntsTimer();
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

    public void updateCurrentImage(Image newImage) {
        if (this.currentImage != null) {
            int index = this.importedImages.indexOf(this.currentImage);
            if (index != -1) {
                this.importedImages.set(index, newImage);
            } else {
                // If the image wasn't in the list, just add the new one
                this.importedImages.add(newImage);
            }
        } else {
            // If there was no image, just add it
            this.importedImages.add(newImage);
        }

        // Set the new image as the current one and repaint
        setCurrentImage(newImage);
        repaint();
    }

    public ActionHistory getActionHistory() {
        return this.actionHistory;
    }
    // use Pair, java generic to generalize an object for selectionTool functinality above
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
