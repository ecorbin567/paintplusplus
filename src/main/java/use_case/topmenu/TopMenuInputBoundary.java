package use_case.topmenu;

public interface TopMenuInputBoundary {
    void setBufferedImage (TopMenuInputData inputData);
    void setFile(TopMenuInputData inputData);
    void undoDrawable(TopMenuInputData inputData);
    void redoDrawable(TopMenuInputData inputData);
    void zoomIn(TopMenuInputData inputData);
    void zoomOut(TopMenuInputData inputData);
    void save(TopMenuInputData inputData);
    void saveOnline(TopMenuInputData inputData);
}
