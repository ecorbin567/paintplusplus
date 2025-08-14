package interface_adapter.topmenu.saveonline;

import interface_adapter.topmenu.saveonline.save.SaveOnlineController;
import org.junit.jupiter.api.Test;
import use_case.topmenu.save.SaveOnlineInputBoundary;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SaveOnlineControllerTest {

    /** Simple spy interactor to capture forwarded args. */
    static class SpyInteractor implements SaveOnlineInputBoundary {
        String lastUsername;
        BufferedImage lastImage;
        int calls = 0;

        @Override
        public void saveCanvasOnline(String username, BufferedImage image) {
            this.lastUsername = username;
            this.lastImage = image;
            calls++;
        }
    }

    @Test
    void handleSaveOnlineButtonPress_forwardsArgsToInteractor() {
        SpyInteractor spy = new SpyInteractor();
        SaveOnlineController controller = new SaveOnlineController(spy);

        String username = "alice";
        BufferedImage img = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);

        controller.handleSaveOnlineButtonPress(img, username);

        assertEquals(1, spy.calls, "Interactor should be called exactly once");
        assertSame(img, spy.lastImage, "Image reference should be forwarded unchanged");
        assertEquals(username, spy.lastUsername, "Username should be forwarded unchanged");
    }
}
