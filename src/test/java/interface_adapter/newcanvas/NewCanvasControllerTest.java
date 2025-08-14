package interface_adapter.newcanvas;

import org.junit.jupiter.api.Test;
import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInputData;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewCanvasControllerTest {

    // ---- Spy interactor ----
    private static class SpyInteractor implements NewCanvasInputBoundary {
        int executeCalls = 0;
        int executeImportCalls = 0;
        int switchToSignupCalls = 0;

        NewCanvasInputData last;

        @Override
        public void execute(NewCanvasInputData newCanvasInputData) {
            executeCalls++;
            last = newCanvasInputData;
        }

        @Override
        public void executeImportExistingCanvas(NewCanvasInputData newCanvasInputData) {
            executeImportCalls++;
            last = newCanvasInputData;
        }

        @Override
        public void switchToSignupView() {
            switchToSignupCalls++;
        }

        @Override
        public List<BufferedImage> getUserCanvases(NewCanvasInputData newCanvasInputData) {
            // Controller doesn't call this (no method for it yet).
            return Collections.emptyList();
        }
    }

    // ---- Reflection helpers to read private fields (since accessors are package-private) ----
    private static String usernameOf(NewCanvasInputData d) {
        return getPrivateStringField(d, "username");
    }

    private static String passwordOf(NewCanvasInputData d) {
        return getPrivateStringField(d, "password");
    }

    private static String getPrivateStringField(Object obj, String fieldName) {
        try {
            Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return (String) f.get(obj);
        } catch (ReflectiveOperationException e) {
            fail("Reflection failed for field: " + fieldName + " - " + e.getMessage());
            return null; // unreachable
        }
    }

    @Test
    void execute_withoutImport_forwardsCredentials() {
        SpyInteractor spy = new SpyInteractor();
        NewCanvasController controller = new NewCanvasController(spy);

        controller.execute("alice", "pw123");

        assertEquals(1, spy.executeCalls);
        assertEquals(0, spy.executeImportCalls);
        assertNotNull(spy.last);

        assertEquals("alice", usernameOf(spy.last));
        assertEquals("pw123", passwordOf(spy.last));
        assertNull(spy.last.getImportedCanvas(), "Imported canvas should be null for basic execute()");
    }

    @Test
    void execute_withImport_forwardsAllArgsToImportPath() {
        SpyInteractor spy = new SpyInteractor();
        NewCanvasController controller = new NewCanvasController(spy);
        BufferedImage img = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);

        controller.execute("bob", "secret", img);

        assertEquals(0, spy.executeCalls);
        assertEquals(1, spy.executeImportCalls);
        assertNotNull(spy.last);

        assertEquals("bob", usernameOf(spy.last));
        assertEquals("secret", passwordOf(spy.last));
        assertSame(img, spy.last.getImportedCanvas(), "Exact BufferedImage instance should be forwarded");
    }

    @Test
    void switchToSignupView_delegates() {
        SpyInteractor spy = new SpyInteractor();
        NewCanvasController controller = new NewCanvasController(spy);

        controller.switchToSignupView();

        assertEquals(1, spy.switchToSignupCalls);
        assertEquals(0, spy.executeCalls);
        assertEquals(0, spy.executeImportCalls);
    }

    @Test
    void controller_doesNotMutateCredentials() {
        SpyInteractor spy = new SpyInteractor();
        NewCanvasController controller = new NewCanvasController(spy);

        controller.execute("  User  ", "  P@ss  ");

        assertEquals("  User  ", usernameOf(spy.last));
        assertEquals("  P@ss  ", passwordOf(spy.last));
    }
}
