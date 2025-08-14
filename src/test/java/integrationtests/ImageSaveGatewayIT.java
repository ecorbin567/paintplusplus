package integrationtests;

import data_access.ImageSaveGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ImageSaveGatewayIT {

    @TempDir
    Path tempDir;

    private static BufferedImage sampleImage(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, w - 1, h - 1);
        g.dispose();
        return img;
    }

    @Test
    void saveImage_writesPngFile_andIsReadable() throws Exception {
        ImageSaveGateway gateway = new ImageSaveGateway();

        BufferedImage original = sampleImage(120, 80);
        File out = tempDir.resolve("saved.png").toFile();

        gateway.saveImage(original, out);  // integration: real disk write

        assertTrue(out.exists(), "PNG file should be created on disk");
        BufferedImage reloaded = ImageIO.read(out);
        assertNotNull(reloaded, "ImageIO should read back the saved PNG");
        assertEquals(120, reloaded.getWidth());
        assertEquals(80, reloaded.getHeight());
        // (Optional) quick pixel sanity check
        assertEquals(original.getRGB(0, 0), reloaded.getRGB(0, 0));
    }

    @Test
    void saveImage_throwsIOException_whenParentDirMissing() {
        ImageSaveGateway gateway = new ImageSaveGateway();

        BufferedImage img = sampleImage(10, 10);
        // Parent dir does not exist -> FileNotFoundException/IOException
        File target = tempDir.resolve("no_such_dir").resolve("img.png").toFile();
        assertFalse(target.getParentFile().exists());

        IOException ex = assertThrows(IOException.class, () -> gateway.saveImage(img, target));
        assertNotNull(ex.getMessage());
    }
}
