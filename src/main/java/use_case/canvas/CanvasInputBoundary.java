package use_case.canvas;

import use_case.tooluse.ToolUseInputData;

public interface CanvasInputBoundary {
    void useTool(ToolUseInputData inputData);
    void selectRegion(CanvasInputData canvasInputData);
    void scaleCanvas(CanvasInputData canvasInputData);
    void eraseStroke(CanvasInputData canvasInputData);
    void redoAction(CanvasInputData canvasInputData);
    void undoAction(CanvasInputData canvasInputData);
}

