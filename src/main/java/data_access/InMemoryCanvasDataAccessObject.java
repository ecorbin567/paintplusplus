package data_access;

import entity.User;
import use_case.getcanvas.GetCanvasUserDataAccessInterface;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCanvasDataAccessObject implements GetCanvasUserDataAccessInterface {

    private final Map<String, List<BufferedImage>> usersDocumentsMap = new HashMap<>();

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
