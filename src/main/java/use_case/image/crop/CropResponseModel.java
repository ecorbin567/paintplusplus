package use_case.image.crop;

import entity.Image;

import java.util.List;

public class CropResponseModel {

    private final List<Image> croppedImage;

    public CropResponseModel(List<Image> croppedImages) {
        this.croppedImage = croppedImages;
    }

    public List<Image> getImage() {
        return croppedImage;
    }
}
