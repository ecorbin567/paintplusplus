package use_case.topmenu;

import java.awt.image.BufferedImage;
import java.io.File;

public interface FileSaveGateway {
    void saveImage(BufferedImage image, File file) throws ImageSaveException;
}
