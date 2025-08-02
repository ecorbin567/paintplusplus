package use_case.getcanvas;

import data_access.UserDataAccessInterface;
import entity.ActionHistory;
import entity.User;

import java.awt.image.BufferedImage;
import java.util.List;

/** Use case: retrieving all the documents for a certain user. **/
public class GetCanvasInteractor implements GetCanvasInputBoundary {
    private final GetCanvasUserDataAccessInterface canvasDataAccessObject;
    private final GetCanvasOutputBoundary getCanvasPresenter;

    public GetCanvasInteractor(GetCanvasUserDataAccessInterface canvasDataAccessInterface,
                               GetCanvasOutputBoundary getCanvasOutputBoundary) {
        this.canvasDataAccessObject = canvasDataAccessInterface;
        this.getCanvasPresenter = getCanvasOutputBoundary;
    }

    private List<BufferedImage> getUserCanvases(String username) {
        return canvasDataAccessObject.getAllCanvases(username);
    }

    @Override
    public void execute(GetCanvasInputData getCanvasInputData) {
        getCanvasPresenter.prepareSuccessView();
    }

    @Override
    public void switchToSignupView() {

    }
}
