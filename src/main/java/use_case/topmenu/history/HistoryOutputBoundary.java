package use_case.topmenu.history;

import entity.Drawable;

public interface HistoryOutputBoundary {
    void setDrawables(HistoryOutputData outputData);
    void setCurrentDrawable(HistoryOutputData outputData);
    void setRepaintState(HistoryOutputData outputData);
    Drawable getCurrentDrawable();
}
