package use_case.getcanvas;

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

    @Override
    public void execute(GetCanvasInputData getCanvasInputData) {
        String username = getCanvasInputData.getUsername();
        List<BufferedImage> userCanvases = canvasDataAccessObject.getAllCanvases(username);

        getCanvasPresenter.prepareSuccessView(new GetCanvasOutputData(username, userCanvases));
    }

    @Override
    public void switchToSignupView() {

    }
}
