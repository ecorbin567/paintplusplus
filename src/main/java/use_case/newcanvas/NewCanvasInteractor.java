package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

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
        final String username = newCanvasInputData.getUsername();

        // Make sure to import a blank white canvas to reset lol

        BufferedImage image = null;
        try {

            image = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/images/BLANK_WHITE.png")));
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        final BufferedImage whiteCanvas = image;

        newCanvasPresenter.prepareSuccessView(
                new NewCanvasOutputData(username, whiteCanvas)
        );
    }

    /**
     * Alternative execution where a canvas is immediately imported.
     * @param newCanvasInputData input data
     */
    public void executeImportExistingCanvas(NewCanvasInputData newCanvasInputData) {
        final String username = newCanvasInputData.getUsername();
        final BufferedImage importedCanvas = newCanvasInputData.getImportedCanvas();

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
        final String username = newCanvasInputData.getUsername();

        return canvasDataAccessObject.getAllCanvases(username);
    }
}
