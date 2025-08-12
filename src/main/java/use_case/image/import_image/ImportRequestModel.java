package use_case.image.import_image;

import java.io.File;

/**
 * Obtains information about the file to be imported.
 */
public record ImportRequestModel(File file) {
}
