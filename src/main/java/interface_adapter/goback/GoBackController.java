package interface_adapter.goback;

import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInputData;

/**
 * The controller for the Go Back Use Case.
 */
public class GoBackController {

    private final GoBackInputBoundary goBackUseCaseInteractor;

    public GoBackController(GoBackInputBoundary goBackUseCaseInteractor) {
        this.goBackUseCaseInteractor = goBackUseCaseInteractor;
    }

    /**
     * Executes the Go Back Use Case.
     *
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password, String command) {
        final GoBackInputData goBackInputData = new GoBackInputData(
                username, password);

        goBackUseCaseInteractor.execute(goBackInputData, command);
    }
}
