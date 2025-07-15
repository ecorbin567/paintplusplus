package entity;

import java.awt.*;

public class DrawingCanvasTwo {
    // TODO: this should be called DrawingCanvas but one already exists, can figure that out later.
    // Represents the canvas

    private CanvasState currentState;
    private Tool selectedTool;

    public CanvasState getCurrentState() {
        // not sure if necessary
        return currentState;
    }

    public Image renderCanvas() {
        // feel free to change this to not an Image.
        // we can discuss later
        return currentState.renderCanvas();
    }
}
