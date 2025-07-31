package use_case.newCanvas;

import entity.User;

/**
 * The Login Interactor.
 */
public class NewCanvasInteractor implements NewCanvasInputBoundary {
    private final NewCanvasUserDataAccessInterface userDataAccessObject;
    private final NewCanvasOutputBoundary newCanvasPresenter;

    public NewCanvasInteractor(NewCanvasUserDataAccessInterface userDataAccessInterface,
                               NewCanvasOutputBoundary newCanvasOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.newCanvasPresenter = newCanvasOutputBoundary;
    }

    @Override
    public void execute(NewCanvasInputData loginInputData) {
//        final User user = userDataAccessObject.getUser(loginInputData.getUsername());
//        userDataAccessObject.setCurrentUser(user.getName());
//        final NewCanvasOutputData loginOutputData = new NewCanvasOutputData(user.getName());
        newCanvasPresenter.prepareSuccessView();
    }

    @Override
    public void switchToSignupView() {
        newCanvasPresenter.switchToSignupView();
    }
}
