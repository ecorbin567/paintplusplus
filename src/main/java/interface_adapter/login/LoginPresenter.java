package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.goback.GoBackState;
import interface_adapter.goback.GoBackViewModel;
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
    private final DrawingViewModel drawingViewModel; // need to update drawing view model
    private final GoBackViewModel goBackViewModel; // lol
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          NewCanvasViewModel canvasViewModel,
                          LoginViewModel loginViewModel, DrawingViewModel drawingViewModel, GoBackViewModel goBackViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.newCanvasViewModel = canvasViewModel;
        this.loginViewModel = loginViewModel;
        this.drawingViewModel = drawingViewModel;
        this.goBackViewModel = goBackViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final NewCanvasState myCanvasState = this.newCanvasViewModel.getState();

        // Upon login success, update "canvas select screen" with user's canvases.
        List<BufferedImage> userCanvases = response.getUserCanvases();
        myCanvasState.setCanvases(userCanvases);
        this.newCanvasViewModel.setState(myCanvasState);
        this.newCanvasViewModel.setCanvases(userCanvases);
        this.newCanvasViewModel.firePropertyChanged();

        // Upon login success, notify drawing view of new username login
        this.drawingViewModel.setCurrentUser(response.getUsername());

        // This is getting out of hand
        GoBackState goBackState = this.goBackViewModel.getState();
        goBackState.setUsername(response.getUsername());

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
