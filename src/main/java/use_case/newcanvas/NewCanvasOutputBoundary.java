package use_case.newcanvas;

/**
 * The output boundary for the New Canvas Use Case.
 */
public interface NewCanvasOutputBoundary {

    /**
     * Prepares the success view for the new canvas use case.
     * @param newCanvasOutputData the new canvas output data.
     */
    void prepareSuccessView(NewCanvasOutputData newCanvasOutputData);

    /**
     * Prepares the switch to the sign-up view page.
     */
    void switchToSignupView();
}
