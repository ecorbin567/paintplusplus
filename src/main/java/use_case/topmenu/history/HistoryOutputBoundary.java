package use_case.topmenu.history;

import entity.Drawable;

import java.util.List;

public interface HistoryOutputBoundary {
    void setDrawables(HistoryOutputData outputData);
    void setCurrentDrawable(HistoryOutputData outputData);
    void setRepaintState(HistoryOutputData outputData);
}
