package use_case.topmenu;

public interface TopMenuOutputBoundary {
    void setRepaintState(TopMenuOutputData outputData);
    void setDrawables(TopMenuOutputData outputData);
    void setScale(TopMenuOutputData outputData);
    void setCurrentDrawable(TopMenuOutputData outputData);
}
