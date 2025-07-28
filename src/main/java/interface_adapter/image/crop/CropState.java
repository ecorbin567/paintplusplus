package interface_adapter.image.crop;

public class CropState {

    private final int previousX, previousY, previousWidth, previousHeight;

    public CropState(int x, int y, int width, int height) {
        this.previousX = x;
        this.previousY = y;
        this.previousWidth = width;
        this.previousHeight = height;
    }
}
