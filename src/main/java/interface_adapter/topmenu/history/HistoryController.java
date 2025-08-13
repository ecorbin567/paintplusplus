package interface_adapter.topmenu.history;

import use_case.topmenu.history.HistoryInputBoundary;

public class HistoryController {
    HistoryInputBoundary interactor;
    public HistoryController(HistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleUndoButtonPress() {
        interactor.undoDrawable();
    }

    public void handleRedoButtonPress() {
        interactor.redoDrawable();
    }

    /**
     * Clear action history.
     */
    public void clearHistory() {
        interactor.clearHistory();
    }

}
