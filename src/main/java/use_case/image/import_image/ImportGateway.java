package use_case.image.import_image;

import entity.Image;
import java.io.File;
import java.io.IOException;

public interface ImportGateway {
    Image loadImage(File file) throws IOException;
}
