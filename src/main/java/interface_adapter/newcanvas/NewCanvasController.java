package interface_adapter.newcanvas;

import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInputData;

/**
 * The controller for the New Canvas Use Case.
 */
public class NewCanvasController {

    private final NewCanvasInputBoundary newCanvasUseCaseInteractor;

    public NewCanvasController(NewCanvasInputBoundary newCanvasUseCaseInteractor) {
        this.newCanvasUseCaseInteractor = newCanvasUseCaseInteractor;
    }

    /**
     * Executes the New Canvas Use Case.
     * @param username the username of the user
     * @param password the password of the user
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
