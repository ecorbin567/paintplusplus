package use_case.image.import_image;

import entity.Image;

/**
 * Represents the imported image.
 */
public class ImportResponseModel {
    private final Image image;

    public ImportResponseModel(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
