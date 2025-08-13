package use_case.topmenu.save;

import data_access.CanvasDataAccessInterface;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Minimal tests for SaveOnlineInteractor. */
class SaveOnlineInteractorTest {

    @Test
    void saveCanvasOnline() {
        FakeCanvasDAO dao = new FakeCanvasDAO();
        SaveOnlineInteractor interactor = new SaveOnlineInteractor(dao);

        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        interactor.saveCanvasOnline("alice", img);

        assertTrue(dao.called, "DAO should be invoked.");
        assertEquals("alice", dao.lastUsername, "Username should be forwarded.");
        assertSame(img, dao.lastImage, "Exact image instance should be forwarded.");
    }

    /** Simple test double (no Mockito). */
    private static class FakeCanvasDAO implements CanvasDataAccessInterface {
        boolean called = false;
        String lastUsername = null;
        BufferedImage lastImage = null;

        @Override
        public boolean saveCanvas(String username, BufferedImage image) {
            called = true;
            lastUsername = username;
            lastImage = image;
            return false;
        }

        @Override
        public List<BufferedImage> getAllCanvases(String username) {
            return List.of();
        }
    }
}
