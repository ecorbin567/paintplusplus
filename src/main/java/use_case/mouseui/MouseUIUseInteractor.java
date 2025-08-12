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

    @Override
    public void mouseIsPressed(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT){
            sendMouseOutputData();
            return;
        }
        ToolEnum toolstate = canvasState.getToolState();
        switch(toolstate) {
            case PENCIL, ERASER, CHANGECOLOR -> handleDrawingTool(toolstate, inputData);
        }
        sendMouseOutputData();
    }

    @Override
    public void mouseIsDragged(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT){
            sendMouseOutputData();
            return;
        }
        ToolEnum toolstate = canvasState.getToolState();
        switch(toolstate) {
            case PENCIL, ERASER -> mouseDragPencilEraser(inputData);
        }
        sendMouseOutputData();
    }

    private void handleDrawingTool(ToolEnum toolstate, MouseUIInputData inputData) {
        Color color = Color.BLACK;
        float size = 3f;

        if (toolstate == ToolEnum.PENCIL || toolstate == ToolEnum.CHANGECOLOR) {
            Paintbrush paintbrush = this.canvasState.getPaintbrush();
            color = paintbrush.getColour();
            size = paintbrush.getWidth();
        }
        else if (toolstate == ToolEnum.ERASER) {
            Eraser eraser = this.canvasState.getEraser();
            color = Color.WHITE;
            size = eraser.getWidth();
        }

        StrokeRecord currentStroke = new StrokeRecord(color, size);
        currentStroke.pts.add(inputData.getPoint());
        actionHistory.push(currentStroke);
    }

    @Override
    public void mouseIsReleased(MouseUIInputData inputData) {
        if (canvasState.getToolState() == ToolEnum.SELECT){
            sendMouseOutputData();
            return;
        }
        sendMouseOutputData();
    }

    private void mouseDragPencilEraser(MouseUIInputData inputData) {
        Drawable drawable = actionHistory.getCurrentState();
        if (drawable instanceof StrokeRecord strokeRecord) {
            strokeRecord.pts.add(inputData.getPoint());
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
