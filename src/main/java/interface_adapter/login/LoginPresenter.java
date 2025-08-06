package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.newcanvas.NewCanvasState;
import interface_adapter.newcanvas.NewCanvasViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final NewCanvasViewModel newCanvasViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          NewCanvasViewModel canvasViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.newCanvasViewModel = canvasViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

//        final LoggedInState loggedInState = loggedInViewModel.getState();
//        loggedInState.setUsername(response.getUsername());
//        this.loggedInViewModel.setState(loggedInState);
//        this.loggedInViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(loggedInViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
        final NewCanvasState myCanvasState = this.newCanvasViewModel.getState();

        // Upon login success, update "canvas select screen" with user's canvases.
        List<BufferedImage> userCanvases = response.getUserCanvases();
        myCanvasState.setCanvases(userCanvases);
        this.newCanvasViewModel.setState(myCanvasState);
        this.newCanvasViewModel.setCanvases(userCanvases);
        this.newCanvasViewModel.firePropertyChanged();

        this.viewManagerModel.setState(newCanvasViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
