package use_case.mouseui;

import entity.*;

import java.awt.*;
import java.util.Stack;


public class MouseUIUseInteractor implements MouseUIUseInputBoundary {
    private final CanvasState canvasState;
    private final MouseUIOutputBoundary mouseUIOutputBoundary;
    private final ActionHistory actionHistory;

    public MouseUIUseInteractor(CanvasState canvasState,
                                MouseUIOutputBoundary mouseUIOutputBoundary) {
        this.canvasState = canvasState;
        this.mouseUIOutputBoundary = mouseUIOutputBoundary;
        this.actionHistory = canvasState.getActionHistory();
    }

    // getter method for testing
    public ActionHistory getActionHistory() {
        return actionHistory;
    }

    @Override
    public void mouseIsPressed(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT){
            sendMouseOutputData();
            return;
        }
        ToolEnum toolState = canvasState.getToolState();
        switch(toolState) {
            case PENCIL, ERASER, CHANGECOLOR -> handleDrawingTool(toolState, inputData);
        }
        sendMouseOutputData();
    }

    @Override
    public void mouseIsDragged(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT){
            sendMouseOutputData();
            return;
        }
        ToolEnum toolState = canvasState.getToolState();
        switch(toolState) {
            case PENCIL, ERASER -> mouseDragPencilEraser(inputData);
        }
        sendMouseOutputData();
    }

    private void handleDrawingTool(ToolEnum toolState, MouseUIInputData inputData) {
        Color color = Color.BLACK;
        float size = 3f;

        if (toolState == ToolEnum.PENCIL || toolState == ToolEnum.CHANGECOLOR) {
            Paintbrush paintbrush = this.canvasState.getPaintbrush();
            color = paintbrush.getColour();
            size = paintbrush.getWidth();
        }
        else if (toolState == ToolEnum.ERASER) {
            Eraser eraser = this.canvasState.getEraser();
            color = Color.WHITE;
            size = eraser.getWidth();
        }

        StrokeRecord currentStroke = new StrokeRecord(color, size);
        currentStroke.getPts().add(inputData.getPoint());
        this.actionHistory.push(currentStroke);
    }

    @Override
    public void mouseIsReleased(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT) {
            sendMouseOutputData();
            return;
        }
        sendMouseOutputData();
    }

    private void mouseDragPencilEraser(MouseUIInputData inputData) {
        Drawable drawable = actionHistory.getCurrentState();
        if (drawable instanceof StrokeRecord strokeRecord) {
            strokeRecord.getPts().add(inputData.getPoint());
        }
    }

    private void sendMouseOutputData() {
        Stack<Drawable> undoStack = this.actionHistory.getUndoStack();
        boolean state = !undoStack.isEmpty();
        Drawable currentDrawable = actionHistory.getCurrentState();
        MouseUIOutputData outputData = new MouseUIOutputData(undoStack, state, currentDrawable);
        mouseUIOutputBoundary.setDrawableState(outputData);
        mouseUIOutputBoundary.setRepaintState(outputData);
        mouseUIOutputBoundary.setCurrentDrawable(outputData);
    }
}