package use_case.image.rotate;

import java.util.List;

import entity.Image;

/**
 * Presents the rotated image.
 */
public class RotateResponseModel {
    private final List<Image> rotatedImage;

    public RotateResponseModel(List<Image> rotatedImage) {
        this.rotatedImage = rotatedImage;
    }

    public List<Image> getImage() {
        return rotatedImage;
    }
}
