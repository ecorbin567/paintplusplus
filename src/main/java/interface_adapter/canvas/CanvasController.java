package interface_adapter.canvas;

import entity.ToolEnum;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIInputData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasController {
    MouseUIUseInputBoundary mouseUIUseInputBoundary;
    ToolUseInputBoundary toolUseInputBoundary;

    // TODO: Oscar's changes removed TopMenuInputBoundary.
    //  Josh's save online needed it. Need to redelete it
    public CanvasController(MouseUIUseInputBoundary mouseUIUseInputBoundary,
                            ToolUseInputBoundary toolUseInputBoundary) {
        this.mouseUIUseInputBoundary = mouseUIUseInputBoundary;
        this.toolUseInputBoundary = toolUseInputBoundary;
    }

    public void handlePencilButtonPress(ToolEnum tool){
        ToolUseInputData inputData = new ToolUseInputData(tool);
        toolUseInputBoundary.setTool(inputData);
    }

    public void handlePencilSliderButton(ToolEnum tool, float value){
        ToolUseInputData inputData = new ToolUseInputData(tool, value);
        toolUseInputBoundary.setTool(inputData);
        toolUseInputBoundary.setSize(inputData);
    }

    public void handleEraserButtonPress(ToolEnum tool){
        ToolUseInputData inputData = new ToolUseInputData(tool);
        toolUseInputBoundary.setTool(inputData);
    }

    public void handleEraserSliderButton(ToolEnum tool, float value){
        ToolUseInputData inputData = new ToolUseInputData(tool, value);
        toolUseInputBoundary.setTool(inputData);
        toolUseInputBoundary.setSize(inputData);
    }

    public void handleMousePressed(Point point){
        MouseUIInputData inputData = new MouseUIInputData(point);
        mouseUIUseInputBoundary.mouseIsPressed(inputData);
    }

    public void handleMouseDragged(Point point){
        MouseUIInputData inputData = new MouseUIInputData(point);
        mouseUIUseInputBoundary.mouseIsDragged(inputData);
    }

    public void handleMouseReleased(Point point, BufferedImage image){
        MouseUIInputData inputData = new MouseUIInputData(point, image);
        mouseUIUseInputBoundary.mouseIsReleased(inputData);
    }

    public void handleSelectButtonPress(ToolEnum tool){
        ToolUseInputData inputData = new ToolUseInputData(tool);
        toolUseInputBoundary.setTool(inputData);
    }
}


