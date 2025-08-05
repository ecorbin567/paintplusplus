package use_case.getcanvas;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface GetCanvasInputBoundary {

    /**
     * Executes the login use case.
     * @param getCanvasInputData the input data
     */
    void execute(GetCanvasInputData getCanvasInputData);
    void switchToSignupView();
}
