package use_case.image.resize;

import entity.Image;
import java.util.List;

/**
 * Presents the resized image.
 */
public class ResizeResponseModel {
    private final List<Image> resizedImage;

    public ResizeResponseModel( List<Image> resizedImage) {
        this.resizedImage = resizedImage;
    }

    public  List<Image> getImages() { return resizedImage; }
}
