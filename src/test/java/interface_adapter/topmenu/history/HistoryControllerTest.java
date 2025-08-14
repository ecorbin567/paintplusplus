package interface_adapter.topmenu.history;

import org.junit.jupiter.api.Test;
import use_case.topmenu.history.HistoryInputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class HistoryControllerTest {

    // Simple spy interactor to record calls
    private static class SpyInteractor implements HistoryInputBoundary {
        int undoCalls = 0;
        int redoCalls = 0;
        int clearCalls = 0;

        @Override public void undoDrawable() { undoCalls++; }
        @Override public void redoDrawable() { redoCalls++; }
        @Override public void clearHistory() { clearCalls++; }
    }

    @Test
    void handleUndoButtonPress_callsInteractorUndo() {
        SpyInteractor spy = new SpyInteractor();
        HistoryController controller = new HistoryController(spy);

        controller.handleUndoButtonPress();

        assertEquals(1, spy.undoCalls, "Undo should be delegated exactly once");
        assertEquals(0, spy.redoCalls, "Redo should not be called");
        assertEquals(0, spy.clearCalls, "Clear should not be called");
    }

    @Test
    void handleRedoButtonPress_callsInteractorRedo() {
        SpyInteractor spy = new SpyInteractor();
        HistoryController controller = new HistoryController(spy);

        controller.handleRedoButtonPress();

        assertEquals(1, spy.redoCalls, "Redo should be delegated exactly once");
        assertEquals(0, spy.undoCalls, "Undo should not be called");
        assertEquals(0, spy.clearCalls, "Clear should not be called");
    }

    @Test
    void clearHistory_callsInteractorClear() {
        SpyInteractor spy = new SpyInteractor();
        HistoryController controller = new HistoryController(spy);

        controller.clearHistory();

        assertEquals(1, spy.clearCalls, "Clear should be delegated exactly once");
        assertEquals(0, spy.undoCalls, "Undo should not be called");
        assertEquals(0, spy.redoCalls, "Redo should not be called");
    }

    @Test
    void multipleCalls_incrementCounters() {
        SpyInteractor spy = new SpyInteractor();
        HistoryController controller = new HistoryController(spy);

        controller.handleUndoButtonPress();
        controller.handleUndoButtonPress();
        controller.handleRedoButtonPress();
        controller.clearHistory();
        controller.clearHistory();

        assertEquals(2, spy.undoCalls);
        assertEquals(1, spy.redoCalls);
        assertEquals(2, spy.clearCalls);
    }
}
