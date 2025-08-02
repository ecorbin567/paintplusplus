package use_case.getcanvas;

import data_access.UserDataAccessInterface;
import entity.ActionHistory;
import entity.User;

import java.util.List;

/** Use case: retrieving all the documents for a certain user. **/
public class GetCanvasInteractor implements GetCanvasInputBoundary {
    private final UserDataAccessInterface userDataAccessObject;
    private final GetCanvasOutputBoundary getCanvasPresenter;

    public GetCanvasInteractor(UserDataAccessInterface userDataAccessInterface,
                               GetCanvasOutputBoundary getCanvasOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.getCanvasPresenter = getCanvasOutputBoundary;
    }

    private List<ActionHistory> getUserCanvases(User user) {
        return userDataAccessObject.getAllCanvases(user);
    }

    @Override
    public void execute(GetCanvasInputData getCanvasInputData) {
        getCanvasPresenter.prepareSuccessView();
    }

    @Override
    public void switchToSignupView() {

    }
}
