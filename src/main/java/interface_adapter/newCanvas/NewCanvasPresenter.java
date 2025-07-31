package interface_adapter.newCanvas;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasState;
import interface_adapter.canvas.CanvasViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.newCanvas.NewCanvasOutputBoundary;
import use_case.newCanvas.NewCanvasOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class NewCanvasPresenter implements NewCanvasOutputBoundary {

    private final CanvasViewModel canvasViewModel;
    private final ViewManagerModel viewManagerModel;

    public NewCanvasPresenter(ViewManagerModel viewManagerModel,
                              CanvasViewModel canvasViewModel,
                              NewCanvasViewModel newCanvasViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final CanvasState canvasState = this.canvasViewModel.getState();
        this.canvasViewModel.setState(canvasState);
        this.canvasViewModel.firePropertyChanged();

        this.viewManagerModel.setState(canvasViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
