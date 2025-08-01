package interface_adapter.goback;

import interface_adapter.ViewManagerModel;
import interface_adapter.newcanvas.NewCanvasState;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.goback.GoBackOutputBoundary;

/**
 * The Presenter for the Go Back Use Case.
 */
public class GoBackPresenter implements GoBackOutputBoundary {

    private final NewCanvasViewModel newCanvasViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public GoBackPresenter(ViewManagerModel viewManagerModel,
                           NewCanvasViewModel newCanvasViewModel,
                           SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.newCanvasViewModel = newCanvasViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(String command) {
        if (command.equals("goBack")) {
            final NewCanvasState canvasState = this.newCanvasViewModel.getState();
            this.newCanvasViewModel.setState(canvasState);
            this.newCanvasViewModel.firePropertyChanged();

            this.viewManagerModel.setState(newCanvasViewModel.getViewName());
        }
        else if (command.equals("logOut")) {
            final SignupState signupState = this.signupViewModel.getState();
            this.signupViewModel.setState(signupState);
            this.signupViewModel.firePropertyChanged();

            this.viewManagerModel.setState(signupViewModel.getViewName());
        }
        this.viewManagerModel.firePropertyChanged();
    }
}
