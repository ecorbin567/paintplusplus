package use_case.topmenu;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileSaveGateway implements FileSaveGateway {

    @Override
    public void saveImage(BufferedImage image, File file) throws ImageSaveException {
        try {
//            System.out.println("Saving image to " + file.getAbsolutePath());
        ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new ImageSaveException("Failed to Save Image", e);
        }
    }
}

class ImageSaveException extends Exception {
    public ImageSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}