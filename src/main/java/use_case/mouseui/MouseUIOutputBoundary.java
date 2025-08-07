package use_case.mouseui;

public interface MouseUIOutputBoundary {
    void setRepaintState(MouseUIOutputData outputData);
    void setDrawableState(MouseUIOutputData outputData);
    void setCurrentDrawable(MouseUIOutputData outputData);
}
