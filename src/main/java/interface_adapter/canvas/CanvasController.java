package interface_adapter.canvas;

import entity.ToolSelectionState;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;

import java.awt.*;

public class CanvasController {
    ToolSelectionState toolSelectionState;
    ToolUseInputBoundary toolUseInputBoundary;

    public CanvasController(ToolSelectionState toolSelectionState,
                            ToolUseInputBoundary toolUseInputBoundary) {
        this.toolSelectionState = toolSelectionState;
        this.toolUseInputBoundary = toolUseInputBoundary;
    }


    public void handleMousePressed(Point point){
        ToolUseInputData inputData = new ToolUseInputData(
                toolSelectionState.getTool(),
                toolSelectionState.getToolSize(),
                toolSelectionState.getSelectedColor(),
                point);

        toolUseInputBoundary.useTool(inputData);
    }

    public void handleMouseDragged(Point point){
        ToolUseInputData inputData = new ToolUseInputData(
                toolSelectionState.getTool(),
                toolSelectionState.getToolSize(),
                toolSelectionState.getSelectedColor(),
                point);

        toolUseInputBoundary.extendStroke(inputData);
    }

    public void handleMouseReleased(Point point){

    }
}
