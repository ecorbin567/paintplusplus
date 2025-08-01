package use_case.image.import_image;

import java.io.File;

/**
 * Obtains information about the file to be imported.
 */
public class ImportRequestModel {
    private final File file;

    public ImportRequestModel(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
