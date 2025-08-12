package use_case.newcanvas;

/**
 * The output boundary for the New Canvas Use Case.
 */
public interface NewCanvasOutputBoundary {
    void prepareSuccessView(NewCanvasOutputData newCanvasOutputData);

    void switchToSignupView();
}
