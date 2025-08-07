package interface_adapter.newcanvas;

import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInputData;

import java.awt.image.BufferedImage;

/**
 * The controller for the New Canvas Screen Use Case.
 */
public class NewCanvasController {

    private final NewCanvasInputBoundary newCanvasUseCaseInteractor;

    public NewCanvasController(NewCanvasInputBoundary newCanvasUseCaseInteractor) {
        this.newCanvasUseCaseInteractor = newCanvasUseCaseInteractor;
    }

    /**
     * Executes the New Canvas Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password) {
        final NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                username, password, null);

        newCanvasUseCaseInteractor.execute(newCanvasInputData);
    }

    public void execute(String username, String password, BufferedImage importedCanvas) {
        final NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                username, password, importedCanvas
        );
        newCanvasUseCaseInteractor.executeImportExistingCanvas(newCanvasInputData);
    }

    public void switchToSignupView() {
        newCanvasUseCaseInteractor.switchToSignupView();
    }
}
