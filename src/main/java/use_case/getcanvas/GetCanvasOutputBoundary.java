package use_case.getcanvas;

/**
 * The output boundary for the Login Use Case.
 */
public interface GetCanvasOutputBoundary {
    void prepareSuccessView(GetCanvasOutputData outputData);

    void switchToSignupView();
}
