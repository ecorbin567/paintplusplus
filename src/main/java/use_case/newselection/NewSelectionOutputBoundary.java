package use_case.newselection;

/**
 * The output boundary for the new Selection Use Case.
 */
public interface NewSelectionOutputBoundary {

    /**
     * Prepares the extracted bitmap of the current selection for the UI display.
     * @param selectOutputData the selected output data.
     */
    void setSelectionImage(NewSelectionOutputData selectOutputData);

    /**
     * Prepares the current position and size of the selection rectangle.
     * @param selectOutputData the selected output data.
     */
    void setSelectionBounds(NewSelectionOutputData selectOutputData);

    /**
     * Prepares whether if an existing selection is currently being dragged.
     * @param selectOutputData the selected output data.
     */
    void setIsDraggingSelection(NewSelectionOutputData selectOutputData);

    /**
     * Prepares whether if any active selection exists.
     * @param selectOutputData the selected output data.
     */
    void setHasSelection(NewSelectionOutputData selectOutputData);

    /**
     * Prepares whether the user is currently drawing a new selection.
     * @param selectOutputData the selected output data.
     */
    void setIsDrawing(NewSelectionOutputData selectOutputData);

    /**
     *  Prepares the live selection box bounds of the selection tool while sizing.
     * @param selectOutputData the selected output data.
     */
    void setSelectionToolBounds(NewSelectionOutputData selectOutputData);
}
