package use_case.goback;

/**
 * The Login Interactor.
 */
public class GoBackInteractor implements GoBackInputBoundary {
    private final GoBackUserDataAccessInterface userDataAccessObject;
    private final GoBackOutputBoundary goBackPresenter;

    public GoBackInteractor(GoBackUserDataAccessInterface userDataAccessInterface,
                            GoBackOutputBoundary goBackOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.goBackPresenter = goBackOutputBoundary;
    }

    @Override
    public void execute(GoBackInputData goBackInputData, String command) {
        goBackPresenter.prepareSuccessView(command);
    }
}
