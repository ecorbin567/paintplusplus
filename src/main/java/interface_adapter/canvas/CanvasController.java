package interface_adapter.canvas;

import java.awt.Point;
import java.awt.image.BufferedImage;

import entity.ToolEnum;
import use_case.mouseui.MouseUIInputData;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.newselection.NewSelectionInputBoundary;
import use_case.newselection.NewSelectionInputData;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;

public class CanvasController {
    MouseUIUseInputBoundary mouseUIUseInputBoundary;
    ToolUseInputBoundary toolUseInputBoundary;
    NewSelectionInputBoundary selectionInputBoundary;

    public CanvasController(MouseUIUseInputBoundary mouseUIUseInputBoundary,
                            ToolUseInputBoundary toolUseInputBoundary,
                            NewSelectionInputBoundary selectionInputBoundary) {
        this.mouseUIUseInputBoundary = mouseUIUseInputBoundary;
        this.toolUseInputBoundary = toolUseInputBoundary;
        this.selectionInputBoundary = selectionInputBoundary;
    }

    public void handleTools(ToolEnum tool) {
        final ToolUseInputData inputData = new ToolUseInputData(tool);
        toolUseInputBoundary.setTool(inputData);
    }

    public void handleSlider(ToolEnum tool, float value) {
        final ToolUseInputData inputData = new ToolUseInputData(tool, value);
        toolUseInputBoundary.setTool(inputData);
        toolUseInputBoundary.setSize(inputData);
    }

    public void handleMousePressed(Point point) {
        selectionInputBoundary.execute(
                new NewSelectionInputData(NewSelectionInputData.Action.START, point, null)
        );
        // legacy mouseui code
        final MouseUIInputData inputData = new MouseUIInputData(point);
        mouseUIUseInputBoundary.mouseIsPressed(inputData);
    }

    public void handleMouseDragged(Point point) {
        selectionInputBoundary.execute(
                new NewSelectionInputData(NewSelectionInputData.Action.DRAG, point, null)
        );
        // legacy mouseui code
        final MouseUIInputData inputData = new MouseUIInputData(point);
        mouseUIUseInputBoundary.mouseIsDragged(inputData);
    }

    public void handleMouseReleased(Point point, BufferedImage image) {
        selectionInputBoundary.execute(
                new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, point, image)
        );
        // legacy mouseui code
        final MouseUIInputData inputData = new MouseUIInputData(point, image);
        mouseUIUseInputBoundary.mouseIsReleased(inputData);
    }
}


