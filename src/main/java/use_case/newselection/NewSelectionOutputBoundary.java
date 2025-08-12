package use_case.newselection;

public interface NewSelectionOutputBoundary {
    void setSelectionImage(NewSelectionOutputData selectOutputData);
    void setSelectionBounds(NewSelectionOutputData selectOutputData);
    void setIsDraggingSelection(NewSelectionOutputData selectOutputData);
    void setHasSelection(NewSelectionOutputData selectOutputData);
    void setIsDrawing(NewSelectionOutputData selectOutputData);
    void setSelectionToolBounds(NewSelectionOutputData selectOutputData);
}
