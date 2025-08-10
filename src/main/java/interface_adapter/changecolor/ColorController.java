package interface_adapter.changecolor;

import entity.ToolEnum;
import use_case.changecolor.ChangeColorInputBoundary;
import use_case.changecolor.ChangeColorInputData;

import java.awt.*;

public class ColorController {
    ChangeColorInputBoundary changeColorInteractor;
    public ColorController(ChangeColorInputBoundary changeColorInteractor) {
        this.changeColorInteractor = changeColorInteractor;
    }

    public void handleColorChangeButton(ToolEnum toolName, Color color, String buttonName){
        ChangeColorInputData inputData = new ChangeColorInputData(toolName, color, buttonName);
        changeColorInteractor.changeColor(inputData);
        changeColorInteractor.setTool(inputData);
        changeColorInteractor.setButton(inputData);
    }

    public void handleColorChangeButton(ToolEnum toolName, Color color){
        ChangeColorInputData inputData = new ChangeColorInputData(toolName, color);
        changeColorInteractor.changeColor(inputData);
        changeColorInteractor.setTool(inputData);
    }
}
