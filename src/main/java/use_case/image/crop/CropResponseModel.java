package use_case.image.crop;

import java.util.List;

import entity.Image;

/**
 * Represents the cropped image.
 */
public class CropResponseModel {

    private final List<Image> croppedImage;

    public CropResponseModel(List<Image> croppedImages) {
        this.croppedImage = croppedImages;
    }

    public List<Image> getImage() {
        return croppedImage;
    }
}
