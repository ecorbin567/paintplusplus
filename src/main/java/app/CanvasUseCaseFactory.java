package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.changecolor.ColorController;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackPresenter;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.midmenu.image.ImageFacade;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.topmenu.TopMenuFacade;
import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInteractor;
import use_case.goback.GoBackOutputBoundary;
import use_case.goback.GoBackUserDataAccessInterface;
import view.CanvasView;
import view.DrawingView;

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
     * @param newCanvasViewModel      the NewCanvasViewModel to inject into the CanvasView
     * @param canvasViewModel   the CanvasViewModel to inject into the CanvasView
     * @param signupViewModel      the SignupViewModel to inject into the CanvasView
     * @param goBackViewModel the GoBackViewModel to inject into the CanvasView
     * @param userDataAccessObject the GoBackUserDataAccessInterface to inject into the CanvasView
     * @param imageFacade the ImageFacade
     * @param colorController the ColorController
     * @param drawingView the DrawingView
     * @param controller the CanvasController
     * @param topMenuFacade the TopMenuFacade
     * @return the CanvasView created for the provided input classes
     */

    public static CanvasView create(

            ViewManagerModel viewManagerModel,
            GoBackViewModel goBackViewModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            GoBackUserDataAccessInterface userDataAccessObject,
            ImageFacade imageFacade, ColorController colorController,
            DrawingView drawingView, CanvasController controller,
            CanvasViewModel canvasViewModel,
            TopMenuFacade topMenuFacade) {

        final GoBackController goBackController = createGoBackUseCase(viewManagerModel, newCanvasViewModel,
                signupViewModel, userDataAccessObject);

        return new CanvasView(canvasViewModel, goBackViewModel, goBackController,
                imageFacade, colorController, drawingView, controller, topMenuFacade);
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
