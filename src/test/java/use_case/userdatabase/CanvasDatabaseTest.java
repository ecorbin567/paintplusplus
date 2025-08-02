package use_case.userdatabase;

import data_access.SupabaseCanvasRepository;
import data_access.TestUserRepositoryMethods;
import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CanvasDatabaseTest {
    SupabaseCanvasRepository service = new SupabaseCanvasRepository();
    String testUserName = "JaneDoe12345";
    String testPswd = "security1";

    @Test
    public void testSaveCanvas() {
        User user = new CommonUser(testUserName, testPswd);
        try {
            InputStream is = TestUserRepositoryMethods.class.getResource("/images/wheel.png").openStream();
            BufferedImage image = ImageIO.read(is);

            boolean result = service.saveCanvas(testUserName, image);
            assertTrue(result);
            System.out.println("Saved canvas to " + user.getUsername());

        } catch (Exception e) {
            System.out.println("Loading issue");
        }
    }

    @Test
    public void testGetCanvas() {
        String username = testUserName;

        List<BufferedImage> images = service.getAllCanvases(username);

        System.out.println(images);

        // Assert we retrieved at least one image
        assertNotNull(images, "Image list should not be null");
        assertFalse(images.isEmpty(), "No images retrieved for user: " + username);

        try {
            // Write each image to disk so the tester can visually inspect them
            int i = 1;
            for (BufferedImage img : images) {
                if (img == null) {
                    System.out.println("One of the retrieved images is null");
                }

                File output = new File(System.getProperty("java.io.tmpdir"), "retrieved_image_" + i + ".png");
                ImageIO.write(img, "png", output);
                System.out.println("Wrote image to: " + output.getAbsolutePath());
                i++;
            }
        } catch (Exception e) {
            System.out.println("Saving images problem.");
        }
    }
}
