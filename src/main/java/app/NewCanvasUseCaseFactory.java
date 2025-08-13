package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.newcanvas.NewCanvasController;
import interface_adapter.newcanvas.NewCanvasPresenter;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInteractor;
import use_case.newcanvas.NewCanvasOutputBoundary;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;
import view.MyCanvasesView;

/**
 * This class contains the static factory function for creating the NewCanvasView.
 */
public final class NewCanvasUseCaseFactory {

    /** Prevent instantiation. */
    private NewCanvasUseCaseFactory() {
        //unneeded
    }

    /**
     * Factory function for creating the NewCanvasView.
     * @param viewManagerModel the ViewManagerModel to inject into the NewCanvasView
     * @param newCanvasViewModel the NewCanvasViewModel to inject into the NewCanvasView
     * @param canvasViewModel the CanvasViewModel to inject into the NewCanvasView
     * @param canvasDataAccessObject the NewCanvasUserDataAccessInterface to inject into the NewCanvasView
     * @param signupViewModel the SignupViewModel
     * @return the NewCanvasView created for the provided input classes
     */
    public static MyCanvasesView create(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            CanvasViewModel canvasViewModel,
            SignupViewModel signupViewModel,
            NewCanvasUserDataAccessInterface canvasDataAccessObject) {

        final NewCanvasController newCanvasController = createNewCanvasUseCase(viewManagerModel, newCanvasViewModel,
                canvasViewModel, signupViewModel, canvasDataAccessObject);
        return new MyCanvasesView(newCanvasViewModel, newCanvasController);

    }

    private static NewCanvasController createNewCanvasUseCase(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            CanvasViewModel canvasViewModel,
            SignupViewModel signupViewModel,
            NewCanvasUserDataAccessInterface userDataAccessObject) {

        final NewCanvasOutputBoundary newCanvasOutputBoundary = new NewCanvasPresenter(viewManagerModel,
                canvasViewModel, newCanvasViewModel,
                signupViewModel);
        final NewCanvasInputBoundary newCanvasInteractor = new NewCanvasInteractor(
                userDataAccessObject, newCanvasOutputBoundary);

        return new NewCanvasController(newCanvasInteractor);
    }
}
