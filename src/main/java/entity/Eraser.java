package entity;

/**
 * An eraser.
 */
public class Eraser implements Tool {

    private float width;

    public Eraser(float width) {

        this.width = width;
    }

    public Eraser() {

        this.width = 3f;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
