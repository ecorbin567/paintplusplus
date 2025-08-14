package interface_adapter.image;

import interface_adapter.image.rotate.RotateController;
import org.junit.jupiter.api.Test;
import use_case.image.rotate.RotateInputBoundary;
import use_case.image.rotate.RotateRequestModel;

import static org.junit.jupiter.api.Assertions.*;

public class RotateControllerTest {

    private static class SpyInteractor implements RotateInputBoundary {
        RotateRequestModel last;
        int calls = 0;

        @Override
        public void execute(RotateRequestModel requestModel) {
            calls++;
            last = requestModel;
        }
    }

    @Test
    void execute_forwardsDegreesToInteractor() {
        SpyInteractor spy = new SpyInteractor();
        RotateController controller = new RotateController(spy);

        controller.execute(90.0);

        assertEquals(1, spy.calls, "Interactor should be called once");
        assertNotNull(spy.last, "Request should be passed to interactor");
        assertEquals(90.0, spy.last.degrees(), 1e-9);
    }

    @Test
    void execute_multipleCalls_lastValueObserved() {
        SpyInteractor spy = new SpyInteractor();
        RotateController controller = new RotateController(spy);

        controller.execute(45.0);
        controller.execute(270.0);

        assertEquals(2, spy.calls, "Interactor should be called twice");
        assertEquals(270.0, spy.last.degrees(), 1e-9, "Latest degrees should be forwarded");
    }

    @Test
    void execute_allowsArbitraryValues_passThroughOnly() {
        SpyInteractor spy = new SpyInteractor();
        RotateController controller = new RotateController(spy);

        controller.execute(-30.5); // controller does not validate

        assertEquals(1, spy.calls);
        assertEquals(-30.5, spy.last.degrees(), 1e-9);
    }
}
