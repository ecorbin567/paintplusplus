package use_case.newcanvas;

/**
 * The output boundary for the New Canvas Use Case.
 */
public interface NewCanvasOutputBoundary {
    /**
     * Prep suc view.
     * @param newCanvasOutputData data.
     */
    void prepareSuccessView(NewCanvasOutputData newCanvasOutputData);

    /**
     * Switch.
     */
    void switchToSignupView();
}
