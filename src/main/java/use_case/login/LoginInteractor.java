package use_case.login;

import entity.User;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;
    private final NewCanvasUserDataAccessInterface canvasDataAccessInterface;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           NewCanvasUserDataAccessInterface canvasDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.canvasDataAccessInterface = canvasDataAccessInterface;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.getUser(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.getUser(loginInputData.getUsername());
                userDataAccessObject.setCurrentUser(user.getUsername());

                // Aug 5: feed presenter with user canvas data
                final LoginOutputData loginOutputData = new LoginOutputData(
                        user.getUsername(),
                        false,
                        canvasDataAccessInterface.getAllCanvases(user.getUsername())
                );

                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
