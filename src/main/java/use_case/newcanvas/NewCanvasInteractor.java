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
    public void execute(NewCanvasInputData loginOutputData) {
//        final User user = canvasDataAccessObject.getUser(loginInputData.getUsername());
//        canvasDataAccessObject.setCurrentUser(user.getName());
//        final NewCanvasOutputData loginOutputData = new NewCanvasOutputData(user.getName());
        newCanvasPresenter.prepareSuccessView();
    }

    @Override
    public void switchToSignupView() {
        newCanvasPresenter.switchToSignupView();
    }
}
