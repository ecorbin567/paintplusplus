package use_case.tooluse;

import entity.ToolEnum;

import java.awt.*;
public class ToolUseInputData {
    private final ToolEnum toolName;
    private Float size = null;
    private Color color = null;

    
    public ToolUseInputData(ToolEnum toolName, float size){
        this.toolName = toolName;
        this.size = size;
    }

    public ToolUseInputData(ToolEnum toolName, Color color){
        this.toolName = toolName;
        this.color = color;
    }

    public ToolUseInputData(ToolEnum toolName){
        this.toolName = toolName;
    }

    public ToolEnum getToolName() {
        return toolName;
    }

    public Float getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }


}
