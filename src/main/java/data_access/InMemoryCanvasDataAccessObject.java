package data_access;

import use_case.newcanvas.NewCanvasUserDataAccessInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCanvasDataAccessObject implements CanvasDataAccessInterface, NewCanvasUserDataAccessInterface {

    private final Map<String, List<BufferedImage>> usersDocumentsMap = new HashMap<>();
    public String defaultTestUsername; // For development

    /**
     * Populate with images
     */
    public InMemoryCanvasDataAccessObject() {
        defaultTestUsername = "asdf";
        usersDocumentsMap.put(defaultTestUsername, loadLocalTestImages());

        // System.out.println(usersDocumentsMap);
    }

    private static List<BufferedImage> loadLocalTestImages() {
        List<BufferedImage> images = new ArrayList<>();
        try {
            // Get the /images directory (works in IDE, not in JAR)
            File imageDir = new File(
                    InMemoryCanvasDataAccessObject.class
                            .getResource("/sample-canvases")
                            .toURI()
            );

            // Filter for image files
            File[] imageFiles = imageDir.listFiles(file -> {
                String name = file.getName().toLowerCase();
                return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
            });

            if (imageFiles != null) {
                for (File file : imageFiles) {
                    BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        images.add(img);
                    } else {
                        System.err.println("Failed to read image: " + file.getName());
                    }
                }
            } else {
                System.err.println("Image directory is empty or not a directory.");
            }

        } catch (URISyntaxException | NullPointerException e) {
            System.err.println("Could not locate images directory: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return images;
    }

    public boolean saveCanvas(String username, BufferedImage image) {
        if (usersDocumentsMap.get(username) == null) {
            usersDocumentsMap.put(username, new ArrayList<>());
            usersDocumentsMap.get(username).add(image);
            return true;
        } else {
            usersDocumentsMap.get(username).add(image);
            return false;
        }

    }

    public BufferedImage findCanvasById(String username, int id) {
        return usersDocumentsMap.get(username).get(id);
    }

    public List<BufferedImage> getAllCanvases(String username) {
        return usersDocumentsMap.get(username);
    }
}