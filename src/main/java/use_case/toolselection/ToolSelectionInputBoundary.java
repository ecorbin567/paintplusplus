package use_case.toolselection;

import entity.ToolEnum;

import java.awt.*;

public interface ToolSelectionInputBoundary{
    void selectTool (ToolEnum toolName);
    void selectColor(Color color);
    void selectSize (float size);

}
