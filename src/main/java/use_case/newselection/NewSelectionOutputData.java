package use_case.newselection;


import java.awt.*;
import java.awt.image.BufferedImage;

public class NewSelectionOutputData {
    private final BufferedImage selectionImage;
    private final Rectangle selectionBounds;
    private final boolean hasSelection;
    private final boolean draggingSelection;
    private final boolean isDrawing;
    private final Rectangle selectionToolBounds;

    public NewSelectionOutputData(BufferedImage selectionImage,
                                  Rectangle selectionBounds,
                                  boolean hasSelection,
                                  boolean draggingSelection,
                                  boolean isDrawing,
                                  Rectangle selectionToolBounds) {
        this.selectionImage = selectionImage;
        this.selectionBounds = selectionBounds;
        this.hasSelection = hasSelection;
        this.draggingSelection = draggingSelection;
        this.isDrawing = isDrawing;
        this.selectionToolBounds = selectionToolBounds;
    }

    public BufferedImage getSelectionImage() {
        return selectionImage;
    }

    public Rectangle getSelectionBounds() {
        return selectionBounds;
    }

    public boolean getIsDraggingSelection(){
        return draggingSelection;
    }

    public boolean getHasSelection() {
        return hasSelection;
    }

    public boolean getIsDrawing() {
        return isDrawing;
    }

    public Rectangle getSelectionToolBounds() {
        return selectionToolBounds;
    }
}

