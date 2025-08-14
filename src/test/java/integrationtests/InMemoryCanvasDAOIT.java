package integrationtests;

import data_access.InMemoryCanvasDataAccessObject;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCanvasDAOIT {

    private static BufferedImage img(int w, int h, Color c) {
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(c);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return bi;
    }

    @Test
    void saveAndLoadRoundTrip_sameUser_multipleCanvases() {
        InMemoryCanvasDataAccessObject dao = new InMemoryCanvasDataAccessObject();
        String user = "it-user";

        BufferedImage first  = img(20, 10, Color.RED);
        BufferedImage second = img(10, 20, Color.BLUE);

        // First save for a new user should return true (created new list)
        assertTrue(dao.saveCanvas(user, first), "First save should create user record");

        // Subsequent saves should return false (user list already exists)
        assertFalse(dao.saveCanvas(user, second), "Second save should append to existing user list");

        // Get all canvases back
        List<BufferedImage> all = dao.getAllCanvases(user);
        assertNotNull(all, "Repository must return a non-null list");
        assertEquals(2, all.size(), "Should contain exactly two canvases");

        // The DAO stores the references we pass in (no copies)
        assertSame(first,  all.get(0), "First saved image should be at index 0");
        assertSame(second, all.get(1), "Second saved image should be at index 1");

        // Basic integrity checks on dimensions
        assertEquals(20, all.get(0).getWidth());
        assertEquals(10,  all.get(0).getHeight());
        assertEquals(10,  all.get(1).getWidth());
        assertEquals(20,  all.get(1).getHeight());

        // Spot-check find-by-id helper
        BufferedImage byId0 = dao.findCanvasById(user, 0);
        BufferedImage byId1 = dao.findCanvasById(user, 1);
        assertSame(first,  byId0, "findCanvasById(0) should return the first image");
        assertSame(second, byId1, "findCanvasById(1) should return the second image");
    }
}
