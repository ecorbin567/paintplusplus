package use_case.tooluse;

import entity.CanvasState;
import entity.ToolEnum;

public class ToolUseInteractor implements ToolUseInputBoundary {
    private final CanvasState canvasState;

    public ToolUseInteractor(CanvasState canvasState) {
        this.canvasState = canvasState;
    }

    @Override
    public void setTool(ToolUseInputData inputData) {
        final ToolEnum toolName = inputData.getToolName();
        switch (toolName) {
            case PENCIL, ERASER, SELECT -> this.canvasState.setToolState(toolName);
        }
    }

    @Override
    public void setSize(ToolUseInputData inputData) {
        final Float size = inputData.getSize();
        final ToolEnum toolName = inputData.getToolName();
        if (toolName == ToolEnum.PENCIL) {
            this.canvasState.setPaintBrushSize(size);
        }

        else {
            if (toolName == ToolEnum.ERASER) {
                this.canvasState.setEraserSize(size);
            }
        }
    }
}
