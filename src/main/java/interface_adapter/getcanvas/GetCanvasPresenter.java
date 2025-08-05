package interface_adapter.getcanvas;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasState;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.getcanvas.GetCanvasOutputBoundary;
import use_case.getcanvas.GetCanvasOutputData;
import use_case.newcanvas.NewCanvasOutputBoundary;
import use_case.newcanvas.NewCanvasOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class GetCanvasPresenter implements GetCanvasOutputBoundary {

    private final CanvasViewModel canvasViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public GetCanvasPresenter(ViewManagerModel viewManagerModel,
                              CanvasViewModel canvasViewModel,
                              GetCanvasViewModel newCanvasViewModel,
                              SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.canvasViewModel = canvasViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(GetCanvasOutputData outputData) {
        final CanvasState canvasState = this.canvasViewModel.getState();
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
