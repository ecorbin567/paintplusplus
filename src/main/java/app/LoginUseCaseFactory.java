package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;
import view.LoginView;

/**
 * This class contains the static factory function for creating the LoginView.
 */
public final class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {

    }

    /**
     * Factory function for creating the LoginView.
     *
     * @param viewManagerModel             the ViewManagerModel to inject into the LoginView
     * @param loginViewModel               the LoginViewModel to inject into the LoginView
     * @param canvasViewModel              the NewCanvasViewModel to inject into the LoginView
     * @param userDataAccessObject         the LoginUserDataAccessInterface to inject into the LoginView
     * @param newCanvasDataAccessInterface
     * @return the LoginView created for the provided input classes
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            NewCanvasViewModel canvasViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            NewCanvasUserDataAccessInterface newCanvasDataAccessInterface) {

        final LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel,
                                                                   canvasViewModel, userDataAccessObject,
                newCanvasDataAccessInterface);
        return new LoginView(loginViewModel, loginController);

    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            NewCanvasViewModel canvasViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            NewCanvasUserDataAccessInterface canvasDataAccessInterface) {

        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                                                                           newCanvasViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, canvasDataAccessInterface);

        return new LoginController(loginInteractor);
    }
}
