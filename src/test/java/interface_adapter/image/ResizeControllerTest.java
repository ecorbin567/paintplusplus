package interface_adapter.image;

import interface_adapter.image.resize.ResizeController;
import org.junit.jupiter.api.Test;
import use_case.image.resize.ResizeInputBoundary;
import use_case.image.resize.ResizeRequestModel;

import static org.junit.jupiter.api.Assertions.*;

public class ResizeControllerTest {

    private static class SpyInteractor implements ResizeInputBoundary {
        ResizeRequestModel last;
        int calls = 0;

        @Override
        public void execute(ResizeRequestModel requestModel) {
            calls++;
            last = requestModel;
        }
    }

    @Test
    void execute_forwardsSizeToInteractor() {
        SpyInteractor spy = new SpyInteractor();
        ResizeController controller = new ResizeController(spy);

        controller.execute(640, 480);

        assertEquals(1, spy.calls, "Interactor should be called once");
        assertNotNull(spy.last, "Request model should be passed to interactor");
        assertEquals(640, spy.last.newWidth());
        assertEquals(480, spy.last.newHeight());
    }

    @Test
    void execute_multipleCalls_lastValuesObserved() {
        SpyInteractor spy = new SpyInteractor();
        ResizeController controller = new ResizeController(spy);

        controller.execute(100, 200);
        controller.execute(1920, 1080);

        assertEquals(2, spy.calls, "Interactor should be called for each execute()");
        assertEquals(1920, spy.last.newWidth(), "Latest width should be forwarded");
        assertEquals(1080, spy.last.newHeight(), "Latest height should be forwarded");
    }

    @Test
    void execute_allowsEdgeValues_passThroughOnly() {
        SpyInteractor spy = new SpyInteractor();
        ResizeController controller = new ResizeController(spy);

        controller.execute(0, -1); // controller does not validate; it forwards

        assertEquals(1, spy.calls);
        assertEquals(0, spy.last.newWidth());
        assertEquals(-1, spy.last.newHeight());
    }
}
