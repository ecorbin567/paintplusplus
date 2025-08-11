package interface_adapter.canvas;

import entity.ToolEnum;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIInputData;
import use_case.topmenu.TopMenuInputBoundary;
import use_case.topmenu.TopMenuInputData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CanvasController {
    MouseUIUseInputBoundary mouseUIUseInputBoundary;
    ToolUseInputBoundary toolUseInputBoundary;
    TopMenuInputBoundary topMenuInputBoundary;

    public CanvasController(MouseUIUseInputBoundary mouseUIUseInputBoundary,
                            ToolUseInputBoundary toolUseInputBoundary,
                            TopMenuInputBoundary topMenuInputBoundary) {
        this.mouseUIUseInputBoundary = mouseUIUseInputBoundary;
        this.toolUseInputBoundary = toolUseInputBoundary;
        this.topMenuInputBoundary = topMenuInputBoundary;
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

    public void handleUndoButtonPress(ToolEnum tool){
        System.out.println("Undo Button Controller");
        TopMenuInputData inputData = new TopMenuInputData(tool);
        topMenuInputBoundary.undoDrawable(inputData);
    }

    public void handleRedoButtonPress(ToolEnum tool){
        TopMenuInputData inputData = new TopMenuInputData(tool);
        topMenuInputBoundary.redoDrawable(inputData);
    }

    public void handleSaveButtonPress(ToolEnum tool, BufferedImage image, File file){
        TopMenuInputData inputData = new TopMenuInputData(tool, image, file);
        topMenuInputBoundary.setBufferedImage(inputData);
        topMenuInputBoundary.setFile(inputData);
        topMenuInputBoundary.save(inputData);
    }

    public void handleSaveOnlineButtonPress(BufferedImage image, String username){
        TopMenuInputData inputData = new TopMenuInputData(null, image, null, username);
        topMenuInputBoundary.saveOnline(inputData);
    }

    public void handleZoomInButtonPress(ToolEnum tool){
        TopMenuInputData inputData = new TopMenuInputData(tool);
        topMenuInputBoundary.zoomIn(inputData);
    }

    public void handleZoomOutButtonPress(ToolEnum tool){
        TopMenuInputData inputData = new TopMenuInputData(tool);
        topMenuInputBoundary.zoomOut(inputData);
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


