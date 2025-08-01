package interface_adapter.newcanvas;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasUserState;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.newcanvas.NewCanvasOutputBoundary;

/**
 * The Presenter for the Login Use Case.
 */
public class NewCanvasPresenter implements NewCanvasOutputBoundary {

    private final CanvasViewModel canvasViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public NewCanvasPresenter(ViewManagerModel viewManagerModel,
                              CanvasViewModel canvasViewModel,
                              NewCanvasViewModel newCanvasViewModel,
                              SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.canvasViewModel = canvasViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final CanvasUserState canvasState = this.canvasViewModel.getState();
        this.canvasViewModel.setState(canvasState);
        this.canvasViewModel.firePropertyChanged();

        this.viewManagerModel.setState(canvasViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
