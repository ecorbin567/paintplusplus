package use_case.image.import_image;

import entity.Image;
import java.io.File;
import java.io.IOException;

/**
 * Assists with loading the image to be imported.
 */
public interface ImportGateway {

    /**
     * Loads an image from the specified file.
     * @param file the source file to read
     * @return the loaded domain {@link Image}
     * @throws IOException if the file cannot read or unsupported/invalid format.
     */
    Image loadImage(File file) throws IOException;
}
