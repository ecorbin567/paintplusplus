package use_case.image.import_image;

import entity.Image;

public class ImportResponseModel {
    private final Image image;

    public ImportResponseModel(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
