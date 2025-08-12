package data_access;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import use_case.topmenu.save.SaveFileGateway;

public class ImageSaveGateway implements SaveFileGateway {

    @Override
    public void saveImage(BufferedImage image, File file) throws IOException {
        try {
            System.out.println("Saving image to " + file.getAbsolutePath());
            ImageIO.write(image, "png", file);
        }
        catch (IOException e) {
            throw e;
        }
    }
}
