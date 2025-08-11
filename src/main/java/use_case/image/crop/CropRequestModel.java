package use_case.image.crop;

/**
 * Obtains information from the user about the x and y positions, and the width and height,
 * of the cropped image.
 */
public class CropRequestModel {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public CropRequestModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
