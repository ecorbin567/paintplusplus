package entity;

import java.awt.*;

public interface Tool extends DrawableElement {

    // A further specific interface for tools like pencils and erasers.
    // TODO: Perhaps Paintbrush and Eraser could be reconfigured to implement Tool instead of DrawableElement.

    float getWidth();
    Color getColour();
}
