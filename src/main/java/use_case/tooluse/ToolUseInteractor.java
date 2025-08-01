package use_case.tooluse;

import entity.CanvasState;
import entity.Drawable;
import entity.StrokeRecord;


public class ToolUseInteractor implements ToolUseInputBoundary{
    private final CanvasState canvasState;
    private final ToolUseOutputBoundary toolUseOutputBoundary;

    public ToolUseInteractor (CanvasState canvasState, ToolUseOutputBoundary toolUseOutputBoundary) {
        this.canvasState = canvasState;
        this.toolUseOutputBoundary = toolUseOutputBoundary;
    }

    @Override
    public void useTool(ToolUseInputData inputData) {
        StrokeRecord currentStroke = new StrokeRecord(inputData.getColor(), inputData.getSize());
        currentStroke.pts.add(inputData.getPoint());
        canvasState.addActionHistory(currentStroke);
    }

    @Override
    public void extendStroke(ToolUseInputData inputData) {
        Drawable drawable = canvasState.getCurrentDrawable();
        if (drawable != null) {
            if (drawable instanceof StrokeRecord strokeRecord){
                strokeRecord.pts.add(inputData.getPoint());
                canvasState.setCurrentDrawable(strokeRecord);
            }
        }
    }
}
