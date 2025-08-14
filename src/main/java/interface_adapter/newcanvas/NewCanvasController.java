package interface_adapter.newcanvas;

import java.awt.image.BufferedImage;

import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInputData;

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

    /**
     * Execute new canvas with import.
     * @param username username
     * @param password password
     * @param importedCanvas canvas to give
     */
    public void execute(String username, String password, BufferedImage importedCanvas) {
        final NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                username, password, importedCanvas
        );
        newCanvasUseCaseInteractor.executeImportExistingCanvas(newCanvasInputData);
    }

    /**
     * Switch to signup view.
     */
    public void switchToSignupView() {
        newCanvasUseCaseInteractor.switchToSignupView();
    }
}
