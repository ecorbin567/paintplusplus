package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The New Canvas Interactor.
 * New Canvas is to be merged with Get Canvas; it is the same screen.
 */
public class NewCanvasInteractor implements NewCanvasInputBoundary {
    private final NewCanvasUserDataAccessInterface canvasDataAccessObject;
    private final NewCanvasOutputBoundary newCanvasPresenter;

    public NewCanvasInteractor(NewCanvasUserDataAccessInterface canvasDataAccessInterface,
                               NewCanvasOutputBoundary newCanvasOutputBoundary) {
        this.canvasDataAccessObject = canvasDataAccessInterface;
        this.newCanvasPresenter = newCanvasOutputBoundary;
    }

    @Override
    public void execute(NewCanvasInputData newCanvasInputData) {
        // this was once an inline variable, but the below code was added and then removed.
        String username = newCanvasInputData.getUsername();
        /*String password = newCanvasInputData.getPassword();

        List<BufferedImage> userCanvases = canvasDataAccessObject.getAllCanvases(username);
        */

        newCanvasPresenter.prepareSuccessView(
                new NewCanvasOutputData(username, null)
        );
    }

    /**
     * Alternative execution where a canvas is immediately imported
     * @param newCanvasInputData input data
     */
    public void executeImportExistingCanvas(NewCanvasInputData newCanvasInputData) {
        String username = newCanvasInputData.getUsername();
        BufferedImage importedCanvas = newCanvasInputData.getImportedCanvas();

        newCanvasPresenter.prepareSuccessView(
                new NewCanvasOutputData(username, importedCanvas)
        );
    }

    @Override
    public void switchToSignupView() {
        newCanvasPresenter.switchToSignupView();
    }

    @Override
    public List<BufferedImage> getUserCanvases(NewCanvasInputData newCanvasInputData) {
        String username = newCanvasInputData.getUsername();
        String password = newCanvasInputData.getPassword();

        return canvasDataAccessObject.getAllCanvases(username);
    }
}
