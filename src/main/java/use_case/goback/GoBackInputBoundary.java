package use_case.goback;

/**
 * Input Boundary for actions which are related to returning to the My Canvases view.
 */
public interface GoBackInputBoundary {

    /**
     * Executes the go back use case.
     * @param goBackInputData the input data
     * @param command the button on the file menu from where the request to go back originates
     */
    void execute(GoBackInputData goBackInputData, String command);
}
