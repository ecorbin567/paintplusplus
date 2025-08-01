package use_case.toolselection;

import entity.ToolEnum;
import entity.ToolSelectionState;

import java.awt.*;

public class ToolSelectionInteractor implements ToolSelectionInputBoundary{
    private final ToolSelectionState state;
    public ToolSelectionInteractor(ToolSelectionState state) {
        this.state = state;
    }

    @Override
    public void selectTool(ToolEnum toolName) {
        state.setTool(toolName);
    }

    @Override
    public void selectColor(Color color) {
        state.setSelectedColor(color);
    }

    @Override
    public void selectSize(float size) {
        state.setToolSize(size);
    }
}
