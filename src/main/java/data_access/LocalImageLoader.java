package data_access;

import use_case.image.import_image.ImportGateway;
import entity.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LocalImageLoader implements ImportGateway {
    @Override
    public Image loadImage(File file) throws IOException {
        BufferedImage buffered = ImageIO.read(file);
        return new Image(buffered);
    }
}
