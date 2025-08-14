package use_case.goback;

import java.awt.image.BufferedImage;
import java.util.List;

import data_access.CanvasDataAccessInterface;

/**
 * The Go Back Interactor.
 */
public class GoBackInteractor implements GoBackInputBoundary {
    private final CanvasDataAccessInterface canvasDataAccessObject;
    private final GoBackOutputBoundary goBackPresenter;

    public GoBackInteractor(CanvasDataAccessInterface canvasDataAccessObject,
                            GoBackOutputBoundary goBackOutputBoundary) {
        this.canvasDataAccessObject = canvasDataAccessObject;
        this.goBackPresenter = goBackOutputBoundary;
    }

    @Override
    public void execute(GoBackInputData goBackInputData, String command) {
        String username = goBackInputData.getUsername();
        List<BufferedImage> newListOfImages = canvasDataAccessObject.getAllCanvases(username);

        System.out.println(username);
        System.out.println(newListOfImages);

        goBackPresenter.prepareSuccessView(command, new GoBackOutputData(
                username,
                newListOfImages
        ));
    }
}
