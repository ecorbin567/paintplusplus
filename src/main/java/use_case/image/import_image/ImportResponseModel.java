package use_case.image.import_image;

import entity.Image;
import java.util.List;

/**
 * Represents the imported image.
 */

public record ImportResponseModel(List<Image> image) {
}
