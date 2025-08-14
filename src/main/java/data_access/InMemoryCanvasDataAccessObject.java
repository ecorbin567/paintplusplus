package data_access;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import use_case.newcanvas.NewCanvasUserDataAccessInterface;

/**
 * In-memory implementation of canvas persistence that seeds sample canvases from
 * {@code /sample-canvases} on classpath and groups saved images by username.
 */
public class InMemoryCanvasDataAccessObject implements CanvasDataAccessInterface, NewCanvasUserDataAccessInterface {

    private final Map<String, List<BufferedImage>> usersDocumentsMap = new HashMap<>();
    private String defaultTestUsername;
    // For development

    public InMemoryCanvasDataAccessObject() {
        defaultTestUsername = "asdf";
        usersDocumentsMap.put(defaultTestUsername, loadLocalTestImages());

        // System.out.println(usersDocumentsMap);
    }

    private static List<BufferedImage> loadLocalTestImages() {
        final List<BufferedImage> images = new ArrayList<>();
        try {
            // Get the /images directory (works in IDE, not in JAR)
            final File imageDir = new File(
                    InMemoryCanvasDataAccessObject.class
                            .getResource("/sample-canvases")
                            .toURI()
            );

            // Filter for image files
            final File[] imageFiles = imageDir.listFiles(file -> {
                final String name = file.getName().toLowerCase();
                return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg");
            });

            if (imageFiles != null) {
                for (File file : imageFiles) {
                    final BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        images.add(img);
                    }
                    else {
                        System.err.println("Failed to read image: " + file.getName());
                    }
                }
            }
            else {
                System.err.println("Image directory is empty or not a directory.");
            }

        }
        catch (URISyntaxException ex) {
            System.err.println("Could not locate images directory: " + ex.getMessage());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return images;
    }

    @Override
    public boolean saveCanvas(String username, BufferedImage image) {
        final boolean toReturn;
        if (usersDocumentsMap.get(username) == null) {
            usersDocumentsMap.put(username, new ArrayList<>());
            usersDocumentsMap.get(username).add(image);
            toReturn = true;
        }
        else {
            usersDocumentsMap.get(username).add(image);
            toReturn = false;
        }
        return toReturn;
    }

    /**
     * Find canvas by id.
     *
     * @param username username
     * @param id       id
     * @return the canvas image
     */
    public BufferedImage findCanvasById(String username, int id) {
        return usersDocumentsMap.get(username).get(id);
    }

    /**
     * Returns all canvases for the given user (most-recent appended).
     *
     * @param username the user to look up
     * @return the user’s canvas list, or {@code null} if the user has none
     */
    @Override
    public List<BufferedImage> getAllCanvases(String username) {
        return usersDocumentsMap.get(username);
    }
}
