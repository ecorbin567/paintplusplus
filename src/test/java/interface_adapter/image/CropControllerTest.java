package interface_adapter.image;

import interface_adapter.image.crop.CropController;
import org.junit.jupiter.api.Test;
import use_case.image.crop.CropInputBoundary;
import use_case.image.crop.CropRequestModel;

import static org.junit.jupiter.api.Assertions.*;

public class CropControllerTest {

    private static class SpyInteractor implements CropInputBoundary {
        CropRequestModel last;
        int calls = 0;

        @Override
        public void execute(CropRequestModel requestModel) {
            calls++;
            last = requestModel;
        }
    }

    @Test
    void execute_forwardsParamsToInteractor() {
        SpyInteractor spy = new SpyInteractor();
        CropController controller = new CropController(spy);

        controller.execute(10, 20, 100, 50);

        assertEquals(1, spy.calls, "Interactor should be called exactly once");
        assertNotNull(spy.last, "Request model should be passed to interactor");
        assertEquals(10, spy.last.x());
        assertEquals(20, spy.last.y());
        assertEquals(100, spy.last.width());
        assertEquals(50, spy.last.height());
    }

    @Test
    void execute_allowsZeroAndNegativeValues_passesThrough() {
        SpyInteractor spy = new SpyInteractor();
        CropController controller = new CropController(spy);

        controller.execute(-5, 0, -100, 0);

        assertEquals(1, spy.calls);
        assertEquals(-5, spy.last.x());
        assertEquals(0, spy.last.y());
        assertEquals(-100, spy.last.width());
        assertEquals(0, spy.last.height());
    }

    @Test
    void execute_multipleCalls_updatesWithLatestValues() {
        SpyInteractor spy = new SpyInteractor();
        CropController controller = new CropController(spy);

        controller.execute(1, 2, 3, 4);
        controller.execute(5, 6, 7, 8);

        assertEquals(2, spy.calls);
        assertEquals(5, spy.last.x());
        assertEquals(6, spy.last.y());
        assertEquals(7, spy.last.width());
        assertEquals(8, spy.last.height());
    }
}
