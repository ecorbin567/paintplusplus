package entity;

import java.awt.*;

public interface DrawableElement {

    /**
     * Returns element representation as an image.
     *
     * @return an Image object.
     */
    Image renderAsImage();
}
