package use_case.tooluse;

import entity.CanvasState;
import entity.ToolEnum;

import java.awt.*;


public class ToolUseInteractor implements ToolUseInputBoundary {
    private final CanvasState canvasState;

    ToolUseInteractor(CanvasState canvasState) {
        this.canvasState = canvasState;
    }

    @Override
    public void setTool(ToolUseInputData inputData) {
        ToolEnum toolName = inputData.getToolName();
        this.canvasState.setButtonPressed(toolName);
        switch (toolName) {
            case PENCIL -> this.canvasState.setToolState(toolName);
            case ERASER -> this.canvasState.setToolState(toolName);
            case SELECT -> this.canvasState.setToolState(toolName);
            case CHANGECOLOR -> this.canvasState.setToolState(ToolEnum.PENCIL);
        }
    }

    @Override
    public void setSize(ToolUseInputData inputData) {
        Float size = inputData.getSize();
        ToolEnum toolName = inputData.getToolName();
        if (toolName == ToolEnum.PENCIL) {
            this.canvasState.setPaintBrushSize(size);
        }

        else if (toolName == ToolEnum.ERASER) {
            this.canvasState.setEraserSize(size);
        }
    }

    @Override
    public void setColor(ToolUseInputData inputData) {
        Color color = inputData.getColor();
        this.canvasState.setPaintBrushColor(color);
    }
}
