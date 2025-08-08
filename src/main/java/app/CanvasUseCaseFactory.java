package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackPresenter;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInteractor;
import use_case.goback.GoBackOutputBoundary;
import use_case.goback.GoBackUserDataAccessInterface;
import view.CanvasView;

/**
 * This class contains the static factory function for creating the CanvasView.
 */
public final class CanvasUseCaseFactory {

    /** Prevent instantiation. */
    private CanvasUseCaseFactory() {

    }

    /**
     * Factory function for creating the CanvasView.
     *
     * @param viewManagerModel     the ViewManagerModel to inject into the CanvasView
     * @param goBackViewModel      the NewCanvasViewModel to inject into the CanvasView
     * @param newCanvasViewModel   the CanvasViewModel to inject into the CanvasView
     * @param signupViewModel      the SignupViewModel to inject into the CanvasView
     * @param userDataAccessObject the GoBackUserDataAccessInterface to inject into the CanvasView
     * @param canvasViewModel
     * @return the CanvasView created for the provided input classes
     */
    public static CanvasView create(
            ViewManagerModel viewManagerModel,
            GoBackViewModel goBackViewModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            GoBackUserDataAccessInterface userDataAccessObject,
            CanvasViewModel canvasViewModel) {

        final GoBackController goBackController = createGoBackUseCase(viewManagerModel, newCanvasViewModel,
                signupViewModel, userDataAccessObject);
        return new CanvasView(canvasViewModel, goBackViewModel, goBackController);

    }

    private static GoBackController createGoBackUseCase(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            GoBackUserDataAccessInterface userDataAccessObject) {

        final GoBackOutputBoundary goBackOutputBoundary = new GoBackPresenter(viewManagerModel,
                                                                           newCanvasViewModel,
                                                                            signupViewModel);
        final GoBackInputBoundary goBackInteractor = new GoBackInteractor(
                userDataAccessObject, goBackOutputBoundary);

        return new GoBackController(goBackInteractor);
    }
}
