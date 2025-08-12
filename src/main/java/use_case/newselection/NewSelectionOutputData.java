package use_case.newselection;

import use_case.mouseui.SelectOutputData;

import java.awt.*;
import java.awt.image.BufferedImage;

/** Subclass so we can pass it to existing presenters (LSP holds). */
public class NewSelectionOutputData extends SelectOutputData {
    public NewSelectionOutputData(BufferedImage image,
                                  Rectangle bounds,
                                  boolean hasSelection,
                                  boolean draggingSelection,
                                  boolean isDrawing,
                                  Rectangle toolBounds) {
        super(image, bounds, hasSelection, draggingSelection, isDrawing, toolBounds);
    }
}