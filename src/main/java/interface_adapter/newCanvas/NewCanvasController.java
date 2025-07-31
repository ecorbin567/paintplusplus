package interface_adapter.newCanvas;

import use_case.newCanvas.NewCanvasInputBoundary;
import use_case.newCanvas.NewCanvasInputData;

/**
 * The controller for the Login Use Case.
 */
public class NewCanvasController {

    private final NewCanvasInputBoundary newCanvasUseCaseInteractor;

    public NewCanvasController(NewCanvasInputBoundary newCanvasUseCaseInteractor) {
        this.newCanvasUseCaseInteractor = newCanvasUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                username, password);

        newCanvasUseCaseInteractor.execute(newCanvasInputData);
    }

    public void switchToSignupView() {
        newCanvasUseCaseInteractor.switchToSignupView();
    }
}
