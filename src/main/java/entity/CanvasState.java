package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CanvasState {
    private final Paintbrush paintbrush = new Paintbrush();
    private final Eraser eraser = new Eraser();
    private ToolEnum toolState = ToolEnum.PENCIL;
    private ToolEnum buttonPressed = ToolEnum.PENCIL;
    private Image prevImage;
    private Image currentImage;
    private double scale = 1.0;
    private final ActionHistory actionHistory = new ActionHistory();
    private final SelectionTool selectionTool = new SelectionTool();
    private final List<CanvasState.Pair<BufferedImage, Rectangle>> commitedSelections = new ArrayList<>();
    private double imageHeight;
    private double imageWidth;
    private double imageDegree;
    private double canvasWidth;
    private double canvasHeight;
    private BufferedImage savedImage;
    private File savedImageFile;

    public CanvasState() {

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

    public void addActionHistory(Drawable drawable) {
        this.actionHistory.push(drawable);
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public boolean actionHistoryUndoIsEmpty() {
        return this.actionHistory.getUndoStack().isEmpty();
    }

    public boolean actionHistoryRedoIsEmpty() {
        return this.actionHistory.getUndoStack().isEmpty();
    }

    public double getCanvasWidth() {
        return this.canvasWidth;
    }

    public double getCanvasHeight() {
        return this.canvasHeight;
    }

    public void setToolState(ToolEnum toolState) {
        this.toolState = toolState;
    }

    public ToolEnum getToolState() {
        return this.toolState;
    }

    public void setButtonPressed(ToolEnum buttonPressed) {
        this.buttonPressed = buttonPressed;
    }

    public void setSavedImage(BufferedImage savedImage) {
        this.savedImage = savedImage;
    }

    public void setFilePath(File file){
        this.savedImageFile = file;
    }
}
