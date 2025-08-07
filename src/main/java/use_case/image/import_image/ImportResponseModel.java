package use_case.image.import_image;

import entity.Image;

import java.util.List;

public class ImportResponseModel {
    private final List<Image> image;

    public ImportResponseModel(List<Image>  image) {
        this.image = image;
    }

    public List<Image>  getImage() {
        return image;
    }
}
