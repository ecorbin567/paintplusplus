package data_access;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import use_case.topmenu.save.SaveFileGateway;

/**
 * Concrete gateway that saves images to disk using {@link ImageIO}.
 * Writes PNG files.
 */
public class ImageSaveGateway implements SaveFileGateway {

    /**
     * Saves the given image to the specified file path in PNG format.
     *
     * @param image the in-memory image to persist
     * @param file  the destination file (its parent directories should exist)
     * @throws IOException if writing the image fails for any reason
     */
    @Override
    public void saveImage(BufferedImage image, File file) throws IOException {
        try {
            System.out.println("Saving image to " + file.getAbsolutePath());
            ImageIO.write(image, "png", file);
        }
        catch (IOException ex) {
            throw ex;
        }
    }
}
