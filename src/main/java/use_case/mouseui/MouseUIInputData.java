package use_case.mouseui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MouseUIInputData {
    private final Point point;
    private final BufferedImage image;

    public MouseUIInputData(Point point) {
        this.point = point;
        this.image = null;
    }

    public MouseUIInputData(Point point, BufferedImage image) {
        this.point = point;
        this.image = image;
    }

    public Point getPoint() { return point; }
    public BufferedImage getImage() { return image; }
}
