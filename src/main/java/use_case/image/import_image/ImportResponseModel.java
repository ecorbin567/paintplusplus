package use_case.image.import_image;

import java.util.List;

import entity.Image;

/**
 * Represents the imported image.
 */

public record ImportResponseModel(List<Image> image) {
}
