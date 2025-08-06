package use_case.mouseui;

import entity.*;

import java.awt.*;


public class MouseUIUseInteractor implements MouseUIUseInputBoundary {
    private final CanvasState canvasState;
    private final MouseUIOutputBoundary mouseUIOutputBoundary;

    public MouseUIUseInteractor(CanvasState canvasState, MouseUIOutputBoundary mouseUIOutputBoundary) {
        this.canvasState = canvasState;
        this.mouseUIOutputBoundary = mouseUIOutputBoundary;
    }

    @Override
    public void useTool(MouseUIInputData inputData) {
        ToolEnum toolstate = canvasState.getToolState();
        Color color;
        float size;


        if (toolstate.equals(ToolEnum.ERASER)) {
            Eraser eraser = this.canvasState.getEraser();
            color = Color.WHITE;
            size = eraser.getWidth();
        } else if (toolstate.equals(ToolEnum.PENCIL)) {
            Paintbrush paintbrush = this.canvasState.getPaintbrush();
            color = paintbrush.getColour();
            size = paintbrush.getWidth();
        } else {
            color = Color.WHITE;
            size = 3f;
        }

        StrokeRecord currentStroke = new StrokeRecord(color, size);
        currentStroke.pts.add(inputData.getPoint());
        canvasState.addActionHistory(currentStroke);
        MouseUIOutputData outputData = new MouseUIOutputData(currentStroke, true);
        mouseUIOutputBoundary.setDrawableState(outputData);
        mouseUIOutputBoundary.setRepaintState(outputData);
    }

    @Override
    public void extendStroke(MouseUIInputData inputData) {
        ActionHistory actionHistory = canvasState.getActionHistory();
        Drawable drawable = actionHistory.getCurrentState();
        if (drawable != null) {
            if (drawable instanceof StrokeRecord strokeRecord) {
                strokeRecord.pts.add(inputData.getPoint());
                actionHistory.setCurrentState(strokeRecord);
                MouseUIOutputData outputData = new MouseUIOutputData(strokeRecord, true);
                mouseUIOutputBoundary.setDrawableState(outputData);
                mouseUIOutputBoundary.setRepaintState(outputData);
            }
        }
    }
}
