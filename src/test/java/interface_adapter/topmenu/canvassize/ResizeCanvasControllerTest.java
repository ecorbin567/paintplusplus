package interface_adapter.topmenu.canvassize;

import org.junit.jupiter.api.Test;
import use_case.topmenu.canvassize.SizeInputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class ResizeCanvasControllerTest {

    static class FakeSizeInteractor implements SizeInputBoundary {
        int zoomInCalls = 0;
        int zoomOutCalls = 0;

        @Override public void zoomIn()  { zoomInCalls++; }
        @Override public void zoomOut() { zoomOutCalls++; }
    }

    @Test
    void handleZoomInButtonPress_callsInteractorZoomIn() {
        FakeSizeInteractor fake = new FakeSizeInteractor();
        ResizeCanvasController controller = new ResizeCanvasController(fake);

        controller.handleZoomInButtonPress();

        assertEquals(1, fake.zoomInCalls, "zoomIn should be called exactly once");
        assertEquals(0, fake.zoomOutCalls, "zoomOut should not be called");
    }

    @Test
    void handleZoomOutButtonPress_callsInteractorZoomOut() {
        FakeSizeInteractor fake = new FakeSizeInteractor();
        ResizeCanvasController controller = new ResizeCanvasController(fake);

        controller.handleZoomOutButtonPress();

        assertEquals(0, fake.zoomInCalls, "zoomIn should not be called");
        assertEquals(1, fake.zoomOutCalls, "zoomOut should be called exactly once");
    }
}
