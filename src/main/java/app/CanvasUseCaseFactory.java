package app;

import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackPresenter;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.newcanvas.NewCanvasController;
import interface_adapter.newcanvas.NewCanvasPresenter;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInteractor;
import use_case.goback.GoBackOutputBoundary;
import use_case.goback.GoBackUserDataAccessInterface;
import use_case.newcanvas.NewCanvasInputBoundary;
import use_case.newcanvas.NewCanvasInteractor;
import use_case.newcanvas.NewCanvasOutputBoundary;
import use_case.newcanvas.NewCanvasUserDataAccessInterface;
import use_case.signup.SignupOutputBoundary;
import view.CanvasView;
import view.MyCanvasesView;

/**
 * This class contains the static factory function for creating the NewCanvasView.
 */
public final class CanvasUseCaseFactory {

    /** Prevent instantiation. */
    private CanvasUseCaseFactory() {

    }

    /**
     * Factory function for creating the NewCanvasView.
     * @param viewManagerModel the ViewManagerModel to inject into the NewCanvasView
     * @param goBackViewModel the NewCanvasViewModel to inject into the NewCanvasView
     * @param newCanvasViewModel the CanvasViewModel to inject into the NewCanvasView
     * @param userDataAccessObject the NewCanvasUserDataAccessInterface to inject into the NewCanvasView
     * @return the NewCanvasView created for the provided input classes
     */
    public static CanvasView create(
            ViewManagerModel viewManagerModel,
            GoBackViewModel goBackViewModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            GoBackUserDataAccessInterface userDataAccessObject) {

        final GoBackController goBackController = createGoBackUseCase(viewManagerModel, newCanvasViewModel,
                signupViewModel, userDataAccessObject);
        return new CanvasView(goBackViewModel, goBackController);

    }

    private static GoBackController createGoBackUseCase(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            GoBackUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final GoBackOutputBoundary goBackOutputBoundary = new GoBackPresenter(viewManagerModel,
                                                                           newCanvasViewModel,
                                                                            signupViewModel);
        final GoBackInputBoundary goBackInteractor = new GoBackInteractor(
                userDataAccessObject, goBackOutputBoundary);

        return new GoBackController(goBackInteractor);
    }
}
