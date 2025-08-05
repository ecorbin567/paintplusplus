package use_case.image.resize;

public class ResizeRequestModel {
    private final int newWidth;
    private final int newHeight;

    public ResizeRequestModel(int newWidth, int newHeight) {
        this.newWidth = newWidth;
        this.newHeight = newHeight;
    }

    public int getNewWidth() { return newWidth; }
    public int getNewHeight() { return newHeight; }
}
