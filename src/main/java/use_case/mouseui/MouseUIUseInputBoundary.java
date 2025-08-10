package use_case.mouseui;

public interface MouseUIUseInputBoundary {

    void mouseIsPressed(MouseUIInputData inputData);
    void mouseIsDragged(MouseUIInputData inputData);
    void mouseIsReleased(MouseUIInputData inputData);
}
