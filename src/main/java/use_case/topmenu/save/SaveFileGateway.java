package use_case.topmenu.save;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface SaveFileGateway {
    void saveImage(BufferedImage image, File file) throws IOException;
}

