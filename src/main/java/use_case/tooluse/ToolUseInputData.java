package use_case.tooluse;

import entity.ToolEnum;
import java.awt.*;

public class ToolUseInputData {
    private final ToolEnum tool;
    private final float size;
    private final Color color;
    private final Point point;

    public ToolUseInputData(ToolEnum tool, float size, Color color, Point point) {
        this.tool = tool;
        this.size = size;
        this.color = color;
        this.point = point;
    }

    // Getters
    public ToolEnum getTool() { return tool; }
    public float getSize() { return size; }
    public Color getColor() { return color; }
    public Point getPoint() { return point; }
}
