package use_case.tooluse;

import entity.ToolEnum;

public class ToolUseInputData {
    private final ToolEnum toolName;
    private Float size = null;
    
    public ToolUseInputData(ToolEnum toolName, float size){
        this.toolName = toolName;
        this.size = size;
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


}
