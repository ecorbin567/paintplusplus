package interface_adapter.newselection;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionViewModel {
    private BufferedImage selectionImage;
    private Rectangle selectionBounds;
    private boolean hasSelection;
    private boolean draggingSelection;
    private boolean isDrawing;
    private Rectangle selectionToolBounds;

    public void setBuffedImage(BufferedImage selectionImage) {
        this.selectionImage = selectionImage;
    }
    public BufferedImage getSelectionImage() {
        return selectionImage;
    }

    public void setSelectionBounds(Rectangle selectionBounds) {
        this.selectionBounds = selectionBounds;
    }
    public Rectangle getSelectionBounds() {
        return selectionBounds;
    }
    public void setHasSelection(boolean hasSelection) {
        this.hasSelection = hasSelection;
    }
    public boolean getHasSelection() {
        return hasSelection;
    }

    public void setDraggingSelection(boolean draggingSelection) {
        this.draggingSelection = draggingSelection;
    }
    public boolean getDraggingSelection() {
        return draggingSelection;
    }
    public void setIsDrawing(boolean isDrawing) {
        this.isDrawing = isDrawing;
    }
    public boolean getIsDrawing() {
        return isDrawing;
    }
    public void setSelectionToolBounds(Rectangle selectionToolBounds) {
        this.selectionToolBounds = selectionToolBounds;
    }
    public Rectangle getSelectionToolBounds() {
        return selectionToolBounds;
    }



}
