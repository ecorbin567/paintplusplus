package entity;

/**
 * An eraser.
 */
public class Eraser implements Tool{

    private float width;

    public Eraser(float width) {
        this.width = width;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
