package use_case.mouseui;

import java.awt.*;

public class MouseUIInputData {
    private final Point point;

    public MouseUIInputData(Point point) {
        this.point = point;
    }

    public Point getPoint() { return point; }
}
