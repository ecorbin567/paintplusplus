package use_case.newselection;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NewSelectionInputData {
    public enum Action { START, DRAG, COMMIT, CANCEL }

    private final Action action;
    private final Point point;               // mouse position (nullable for CANCEL)
    private final BufferedImage baseImage;   // required for COMMIT to take subimage

    public NewSelectionInputData(Action action, Point point, BufferedImage baseImage) {
        this.action = action;
        this.point = point;
        this.baseImage = baseImage;
    }

    public Action action() { return action; }
    public Point point() { return point; }
    public BufferedImage baseImage() { return baseImage; }
}
