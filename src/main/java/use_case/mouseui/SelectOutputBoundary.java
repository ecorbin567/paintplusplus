package use_case.mouseui;

public interface SelectOutputBoundary {
    void setSelectionImage(SelectOutputData selectOutputData);
    void setSelectionBounds(SelectOutputData selectOutputData);
    void setIsDraggingSelection(SelectOutputData selectOutputData);
    void setHasSelection(SelectOutputData selectOutputData);
    void setIsDrawing(SelectOutputData selectOutputData);
    void setSelectionToolBounds(SelectOutputData selectOutputData);
}
