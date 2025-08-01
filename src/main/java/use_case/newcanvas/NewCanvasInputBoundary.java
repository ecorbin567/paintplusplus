package use_case.newcanvas;

/**
 * Input Boundary for actions which are related to creating a new canvas.
 */
public interface NewCanvasInputBoundary {

    /**
     * Executes the login use case.
     * @param newCanvasInputData the input data
     */
    void execute(NewCanvasInputData newCanvasInputData);
    void switchToSignupView();
}
