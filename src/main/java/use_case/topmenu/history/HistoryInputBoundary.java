package use_case.topmenu.history;

public interface HistoryInputBoundary {
    void undoDrawable();
    void redoDrawable();

    /**
     * Clear history.
     */
    void clearHistory();
}
