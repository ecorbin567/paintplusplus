package interface_adapter.image;

import interface_adapter.image.import_image.ImportController;

import java.io.File;

public interface ImageFacade {
    void crop(int x, int y, int width, int height);
    void resize(int width, int height);
    void rotate(double degrees);
    void importImage(File file);
    ImportController getImportController();
}
