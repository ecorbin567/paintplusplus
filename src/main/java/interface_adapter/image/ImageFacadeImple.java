package interface_adapter.image;

import java.io.File;

import interface_adapter.image.crop.CropController;
import interface_adapter.image.import_image.ImportController;
import interface_adapter.image.resize.ResizeController;
import interface_adapter.image.rotate.RotateController;

public class ImageFacadeImple implements ImageFacade {
    ResizeController resizeController;
    RotateController rotateController;
    ImportController importController;
    CropController cropController;

    public ImageFacadeImple(ResizeController resizeController,
                            RotateController rotateController,
                            ImportController importController,
                            CropController cropController) {
        this.resizeController = resizeController;
        this.rotateController = rotateController;
        this.importController = importController;
        this.cropController = cropController;
    }

    @Override
    public void crop(int x, int y, int width, int height) {
        cropController.execute(x, y, width, height);
    }

    @Override
    public void resize(int width, int height) {
        resizeController.execute(width, height);
    }

    @Override
    public void rotate(double degrees) {
        rotateController.execute(degrees);
    }

    @Override
    public void importImage(File file) {
        importController.execute(file);
    }

    @Override
    public ImportController getImportController() {
        return importController;
    }

}
