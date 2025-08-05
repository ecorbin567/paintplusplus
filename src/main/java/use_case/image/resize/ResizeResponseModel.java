package use_case.image.resize;

import entity.Image;

public class ResizeResponseModel {
    private final Image resizedImage;

    public ResizeResponseModel(Image resizedImage) {
        this.resizedImage = resizedImage;
    }

    public Image getImage() { return resizedImage; }
}
