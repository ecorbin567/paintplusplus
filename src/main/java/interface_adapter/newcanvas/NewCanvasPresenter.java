package interface_adapter.newcanvas;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasUserState;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.newcanvas.NewCanvasInputData;
import use_case.newcanvas.NewCanvasOutputBoundary;
import use_case.newcanvas.NewCanvasOutputData;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The Presenter for the New Canvas Use Case.
 */
public class NewCanvasPresenter implements NewCanvasOutputBoundary {

    private final CanvasViewModel canvasViewModel;
    private final NewCanvasViewModel newCanvasViewModel; // representing the new canvas view
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;

    public NewCanvasPresenter(ViewManagerModel viewManagerModel,
                              CanvasViewModel canvasViewModel,
                              NewCanvasViewModel newCanvasViewModel,
                              SignupViewModel signupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.canvasViewModel = canvasViewModel;
        this.newCanvasViewModel = newCanvasViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView(NewCanvasOutputData newCanvasOutputData) {
        /* if an imported canvas is provided, import it into canvasViewModel */
        BufferedImage importedCanvas = newCanvasOutputData.getImportedCanvas();

        final CanvasUserState canvasState = this.canvasViewModel.getState();
        if (importedCanvas != null) {
            // fires property change called "initialImportedImage"
            canvasViewModel.setInitialImportedImage(importedCanvas);
        }
        this.canvasViewModel.setState(canvasState);
        this.canvasViewModel.firePropertyChanged();
        this.canvasViewModel.firePropertyChanged("clearhistory");

        this.viewManagerModel.setState(canvasViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
