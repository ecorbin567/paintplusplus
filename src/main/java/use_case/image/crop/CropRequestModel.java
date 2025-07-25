package use_case.image.crop;

import entity.Image;

public class CropRequestModel {

    private final int x, y, width, height;

    public CropRequestModel(int x, int y, int width, int height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
