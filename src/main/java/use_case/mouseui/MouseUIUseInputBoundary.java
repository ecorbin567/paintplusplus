package use_case.mouseui;

/**
 * Mouse UI boundary for actions related to creating a new mouse UI events
 * to drive canvas interactions.
 */
public interface MouseUIUseInputBoundary {

    /**
     * Prepares a mouse press to start the correct tool action at the given point.
     * @param inputData the mouseUI input data.
     */
    void mouseIsPressed(MouseUIInputData inputData);

    /**
     * Prepares a mouse drag to update the in-progress tool action.
     * @param inputData the mouseUi input data.
     */
    void mouseIsDragged(MouseUIInputData inputData);

    /**
     * Prepares a mouse release to finalize the current tool action.
     * @param inputData the mouseUI input data.
     */
    void mouseIsReleased(MouseUIInputData inputData);
}
