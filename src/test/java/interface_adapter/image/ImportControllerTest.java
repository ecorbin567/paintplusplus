package interface_adapter.image;

import interface_adapter.image.import_image.ImportController;
import org.junit.jupiter.api.Test;
import use_case.image.import_image.ImportInputBoundary;
import use_case.image.import_image.ImportRequestModel;

import java.io.File;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class ImportControllerTest {

    private static class SpyInteractor implements ImportInputBoundary {
        ImportRequestModel last;
        int calls = 0;

        @Override
        public void execute(ImportRequestModel request) {
            calls++;
            last = request;
        }
    }

    // Helper that works whether ImportRequestModel is a record (file()) or POJO (getFile()).
    private static File extractFile(ImportRequestModel req) {
        try {
            Method m = req.getClass().getMethod("file");
            return (File) m.invoke(req);
        } catch (ReflectiveOperationException ignore) {
            try {
                Method m = req.getClass().getMethod("getFile");
                return (File) m.invoke(req);
            } catch (ReflectiveOperationException e) {
                fail("ImportRequestModel must expose the selected file via file() or getFile()");
                return null;
            }
        }
    }

    @Test
    void execute_forwardsSelectedFileToInteractor() {
        SpyInteractor spy = new SpyInteractor();
        ImportController controller = new ImportController(spy);

        File chosen = new File("some/path/image.png");
        controller.execute(chosen);

        assertEquals(1, spy.calls, "Interactor should be invoked once");
        assertNotNull(spy.last, "Request model should be created and passed");
        assertEquals(chosen, extractFile(spy.last), "Selected file should be forwarded unchanged");
    }

    @Test
    void execute_allowsNullFile_andPassesThrough() {
        SpyInteractor spy = new SpyInteractor();
        ImportController controller = new ImportController(spy);

        controller.execute(null);

        assertEquals(1, spy.calls);
        assertNull(extractFile(spy.last), "Null file should be passed through unchanged");
    }

    @Test
    void execute_multipleInvocations_updateWithLatestFile() {
        SpyInteractor spy = new SpyInteractor();
        ImportController controller = new ImportController(spy);

        controller.execute(new File("first.png"));
        controller.execute(new File("second.png"));

        assertEquals(2, spy.calls);
        assertEquals(new File("second.png"), extractFile(spy.last),
                "Last call should determine the latest request parameters");
    }
}
