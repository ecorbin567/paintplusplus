package use_case.image.rotate;

import entity.Image;

public class RotateResponseModel {
    private final Image rotatedImage;

    public RotateResponseModel(Image rotatedImage) {
        this.rotatedImage = rotatedImage;
    }

    public Image getImage() { return rotatedImage; }
}
