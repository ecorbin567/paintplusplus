package use_case.toolselection;

import entity.ToolEnum;

public class ToolSelectionInputData {
    private final ToolEnum tool;


    public ToolSelectionInputData(ToolEnum tool) {
        this.tool = tool;
    }

    public ToolEnum getTool() {
        return tool;
    }
}
