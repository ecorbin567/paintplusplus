package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CanvasState {
    private double scale = 1.0;
    //ToolUse
    private final Paintbrush paintbrush = new Paintbrush();
    private final Eraser eraser = new Eraser();
    private final SelectionTool selectionTool = new SelectionTool();

    //ToolState
    private ToolEnum toolState = ToolEnum.PENCIL;
    private ToolEnum buttonPressed = ToolEnum.PENCIL;
    private Image currentImage;

    //ActionHistory
    private final ActionHistory actionHistory = new ActionHistory();

    //importedImage
    private final List<Image> importedImages = new ArrayList<>();

    //Selection Stuff
    private BufferedImage selectionImage = null;
    private Rectangle selectionBounds = null;
    private Rectangle selectionOriginalBounds;
    private boolean hasSelection = false;
    private boolean draggingSelection = false;
    private boolean hasCutOut = false;
    private boolean isDrawing = false;
    private Point dragAnchor = null;
    private List<Rectangle> clearRegions = new ArrayList<>();
    private final List<CanvasState.Pair<BufferedImage, Rectangle>> commitedSelections = new ArrayList<>();

    //Database + Save
    private BufferedImage savedImage = null;
    private File savedImageFile = null;


    public CanvasState() {}

    public static class Pair<A,B> {
        final A first;
        final B second;
        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }


    //ActionHistory Stuff
    public ActionHistory getActionHistory() {
        return this.actionHistory;
    }
    public void addActionHistory(Drawable drawable) {
        this.actionHistory.push(drawable);
    }

    //PaintBrush
    public Paintbrush getPaintbrush() {return paintbrush;}
    public void setPaintBrushSize(float size) {
        this.paintbrush.setWidth(size);
    }
    public void setPaintBrushColor(Color color){
        this.paintbrush.setColour(color);
    }

    //Eraser
    public void setEraserSize(float size) {
        this.eraser.setWidth(size);
    }
    public Eraser getEraser() {return eraser;}

    //Image
    public void setCurrentImage(Image image) {
        this.currentImage = image;
    }
    public Image getCurrentImage() {
        return this.currentImage;
    }
    public List<Image> getImportedImages() {return this.importedImages;}
    public void setImportedImages(List<Image> importedImages) {
        this.importedImages.clear();
    }


    //Setting ToolState
    public void setToolState(ToolEnum toolState) {
        this.toolState = toolState;
    }
    public ToolEnum getToolState() {
        return this.toolState;
    }

    //Setting ButtonPressed
    public void setButtonPressed(ToolEnum buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setSavedImage(BufferedImage savedImage) {
        this.savedImage = savedImage;
    }

    public void setFilePath(File file){
        this.savedImageFile = file;
    }

    //Selection Tool
    public boolean hasSelection() {
        return this.hasSelection;
    }
    public void setHasSelection(boolean hasSelection) {
        this.hasSelection = hasSelection;
    }

    public boolean hasCutOut() {
        return this.hasCutOut;
    }
    public void setHasCutOut(boolean hasCutOut) {
        this.hasCutOut = hasCutOut;
    }

    public boolean draggingSelection() {
        return this.draggingSelection;
    }
    public void setDraggingSelection(boolean draggingSelection) {
        this.draggingSelection = draggingSelection;
    }

    public boolean isDrawing() {
        return this.isDrawing;
    }
    public void setDrawing(boolean drawing) {
        this.isDrawing = drawing;
    }

    public Rectangle getSelectionBounds(){
        return this.selectionBounds;
    }
    public void setSelectionBounds(Rectangle selectionBounds) {
        this.selectionBounds = selectionBounds;
    }

    public Point getDragAnchor(){return this.dragAnchor;}
    public void setDragAnchor(Point dragAnchor){this.dragAnchor = dragAnchor;}

    public List<Rectangle> getClearRegions() {return this.clearRegions;}
    public void setClearRegions(List<Rectangle> clearRegions) {
        this.clearRegions = clearRegions;
    }

    public Rectangle getSelectionOriginalBounds() {return this.selectionOriginalBounds;}
    public void setSelectionOriginalBounds(Rectangle selectionOriginalBounds) {
        this.selectionOriginalBounds = selectionOriginalBounds;
    }

    public BufferedImage getSelectionImage() {return this.selectionImage;}
    public void setSelectionImage(BufferedImage selectionImage) {
        this.selectionImage = selectionImage;
    }

    public SelectionTool getSelectionTool() {
        return this.selectionTool;
    }

    public List<CanvasState.Pair<BufferedImage, Rectangle>> getCommitedSelections() {
        return this.commitedSelections;
    }



}
