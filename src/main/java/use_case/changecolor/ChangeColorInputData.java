package use_case.changecolor;

import entity.ToolEnum;

import java.awt.*;

public class ChangeColorInputData {
    private final ToolEnum toolName;
    private final Color newColor;

    public ChangeColorInputData(ToolEnum toolName, Color newColor){
        this.newColor = newColor;
        this.toolName = toolName;
    }

    public Color getNewColor(){
        return newColor;
    }
}
