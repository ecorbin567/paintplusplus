package app;

import data_access.CanvasDataAccessInterface;
import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.changecolor.ColorController;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackPresenter;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.image.ImageFacade;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.history.HistoryController;
import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInteractor;
import use_case.goback.GoBackOutputBoundary;
import view.CanvasView;
import view.DrawingView;

/**
 * This class contains the static factory function for creating the CanvasView.
 */
public final class CanvasUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private CanvasUseCaseFactory() {

    }

    /**
     * Factory function for creating the CanvasView.
     *
     * @param viewManagerModel       the ViewManagerModel to inject into the CanvasView
     * @param newCanvasViewModel     the NewCanvasViewModel to inject into the CanvasView
     * @param canvasViewModel        the CanvasViewModel to inject into the CanvasView
     * @param signupViewModel        the SignupViewModel to inject into the CanvasView
     * @param goBackViewModel        the GoBackViewModel to inject into the CanvasView
     * @param canvasDataAccessObject the GoBackUserDataAccessInterface to inject into the CanvasView
     * @param imageFacade            the ImageFacade
     * @param colorController        the ColorController
     * @param drawingView            the DrawingView
     * @param controller             the CanvasController
     * @param topMenuFacade          the TopMenuFacade
     * @return the CanvasView created for the provided input classes
     */

    public static CanvasView create(

            ViewManagerModel viewManagerModel,
            GoBackViewModel goBackViewModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            ImageFacade imageFacade, ColorController colorController,
            CanvasDataAccessInterface canvasDataAccessObject,
            DrawingView drawingView, CanvasController controller,
            CanvasViewModel canvasViewModel,
            TopMenuFacade topMenuFacade,
            HistoryController historyController) {

        final GoBackController goBackController = createGoBackUseCase(viewManagerModel, newCanvasViewModel,
                signupViewModel, canvasDataAccessObject);

        return new CanvasView(canvasViewModel, goBackViewModel, goBackController,
                imageFacade, colorController, drawingView, controller, topMenuFacade, historyController);
    }

    private static GoBackController createGoBackUseCase(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            SignupViewModel signupViewModel,
            CanvasDataAccessInterface canvasDataAccessObject) {

        final GoBackOutputBoundary goBackOutputBoundary = new GoBackPresenter(viewManagerModel,
                newCanvasViewModel,
                signupViewModel);
        final GoBackInputBoundary goBackInteractor = new GoBackInteractor(
                canvasDataAccessObject, goBackOutputBoundary);

        return new GoBackController(goBackInteractor);
    }

}
