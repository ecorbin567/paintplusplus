package interface_adapter.image;

import java.io.File;

import interface_adapter.image.import_image.ImportController;

public interface ImageFacade {
    void crop(int x, int y, int width, int height);

    void resize(int width, int height);

    void rotate(double degrees);

    void importImage(File file);

    ImportController getImportController();
}
