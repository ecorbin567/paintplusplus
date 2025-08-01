package entity;

import java.awt.*;

public class ToolSelectionState {
    private ToolEnum tool;
    private float toolSize = 3f;
    private Color selectedColor = Color.BLACK;

    public ToolSelectionState(ToolEnum tool) {
        this.tool = tool;
    }
    public ToolEnum getTool() {
        return tool;
    }

    public void setTool(ToolEnum tool) {
        this.tool = tool;
    }

    public float getToolSize() {
        return toolSize;
    }

    public void setToolSize(float toolSize) {
        this.toolSize = toolSize;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
}
