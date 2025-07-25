package use_case.image.crop;

import entity.Image;

public class CropResponseModel {

    private final Image croppedImage;

    public CropResponseModel(Image croppedImage) {
        this.croppedImage = croppedImage;
    }

    public Image getImage() {
        return croppedImage;
    }
}
