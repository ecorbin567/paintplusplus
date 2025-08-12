package use_case.newcanvas;

import view.MidMenuBar.ImageBar.CropButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

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

        // Make sure to import a blank white canvas to reset lol

        BufferedImage image = null;
        try {

            image = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/images/BLANK_WHITE.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedImage white_canvas = image;

        newCanvasPresenter.prepareSuccessView(
                new NewCanvasOutputData(username, white_canvas)
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
