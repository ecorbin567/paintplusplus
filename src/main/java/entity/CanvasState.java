package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class CanvasState {
    private Paintbrush paintbrush = new Paintbrush();
    private Eraser eraser = new Eraser();
    private ToolSelectionState toolState;
    private Image currentImage;
    private double scale = 1.0;
    private final ActionHistory actionHistory = new ActionHistory();
    private final List<Image> importedImages = new ArrayList<>();
    private SelectionTool selectionTool = new SelectionTool();
    private final List<CanvasState.Pair<BufferedImage, Rectangle>> commitedSelections = new ArrayList<>();
    private double imageHeight;
    private double imageWidth;
    private double imageDegree;
    private double canvasWidth;
    private double canvasHeight;

    public CanvasState(ToolSelectionState toolState) {
        this.toolState = toolState;
    }

    private static class Pair<A,B> {
        final A first;
        final B second;
        Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

    //ActionHistory Stuff
    public ActionHistory getActionHistory() {
        return this.actionHistory;
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

    public Drawable undoActionHistory() {
        return this.actionHistory.undo();
    }

    public Drawable redoActionHistory() {
        return this.actionHistory.redo();
    }

    public void addActionHistory(Drawable drawable) {
        this.actionHistory.push(drawable);
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public List<Image> getImportedImages() {
        return this.importedImages;
    }

    public void clearImportedImages() {
        this.importedImages.clear();
    }

    public void addDrawableElement(Image image){
        this.importedImages.add(image);
        setCurrentImage(image);
    }

    public boolean actionHistoryUndoIsEmpty() {
        return this.actionHistory.getUndoStack().isEmpty();
    }

    public boolean actionHistoryRedoIsEmpty() {
        return this.actionHistory.getUndoStack().isEmpty();
    }

    public boolean imageHistoryIsEmpty() {
        return this.importedImages.isEmpty();
    }

    public double getCanvasWidth() {
        return this.canvasWidth;
    }

    public double getCanvasHeight() {
        return this.canvasHeight;
    }

    public List<Drawable> getCurrentDrawables() {
        return this.actionHistory.getUndoStack();
    }

    public Drawable getCurrentDrawable() {
        return this.actionHistory.getCurrentState();
    }

    public void setCurrentDrawable(Drawable drawable) {
        this.actionHistory.setCurrentState(drawable);
    }


    public Image getLastImage() {
        return this.importedImages.get(this.importedImages.size() - 1);
    }


}
