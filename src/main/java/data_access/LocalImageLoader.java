package data_access;

import use_case.image.import_image.ImportGateway;
import entity.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Implements the ImportGateway interface to load an image from the local file system.
 * This class is responsible for reading an image file and converting it into Image entity.
 */
public class LocalImageLoader implements ImportGateway {

    /**
     * Loads an image from the specified file.
     * @param file the image file to be loaded from the local disk
     * @return an Image entity representing the loaded image
     * @throws IOException if an error occurs during the file reading process,
     * such as the file not being found or not being a valid image format.
     */
    @Override
    public Image loadImage(File file) throws IOException {
        BufferedImage buffered = ImageIO.read(file);
        return new Image(buffered);
    }
}
