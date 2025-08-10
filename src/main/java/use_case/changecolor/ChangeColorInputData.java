package use_case.changecolor;

import entity.ToolEnum;

import java.awt.*;

/**
 * The Input Data for the Change Colour Use Case.
 */
public class ChangeColorInputData {
    private final ToolEnum toolName;
    private final Color newColor;
    private final String buttonName;

    public ChangeColorInputData(ToolEnum toolName, Color newColor, String buttonName) {
        this.newColor = newColor;
        this.toolName = toolName;
        this.buttonName = buttonName;
    }

    public ChangeColorInputData(ToolEnum toolName, Color newColor) {
        this.newColor = newColor;
        this.toolName = toolName;
        this.buttonName = null;
    }

    public Color getNewColor(){
        return newColor;
    }

    public ToolEnum getToolName(){
        return toolName;
    }

    public String getButtonName(){
        return buttonName;
    }
}
