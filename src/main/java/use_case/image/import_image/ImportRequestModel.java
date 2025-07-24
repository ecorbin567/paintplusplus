package use_case.image.import_image;

import java.io.File;

public class ImportRequestModel {
    private final File file;

    public ImportRequestModel(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
