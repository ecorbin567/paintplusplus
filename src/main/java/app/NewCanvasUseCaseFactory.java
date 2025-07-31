package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.newCanvas.NewCanvasController;
import interface_adapter.newCanvas.NewCanvasPresenter;
import interface_adapter.newCanvas.NewCanvasViewModel;
import use_case.newCanvas.NewCanvasInputBoundary;
import use_case.newCanvas.NewCanvasInteractor;
import use_case.newCanvas.NewCanvasOutputBoundary;
import use_case.newCanvas.NewCanvasUserDataAccessInterface;
import view.MyCanvasesView;

/**
 * This class contains the static factory function for creating the NewCanvasView.
 */
public final class NewCanvasUseCaseFactory {

    /** Prevent instantiation. */
    private NewCanvasUseCaseFactory() {

    }

    /**
     * Factory function for creating the NewCanvasView.
     * @param viewManagerModel the ViewManagerModel to inject into the NewCanvasView
     * @param newCanvasViewModel the NewCanvasViewModel to inject into the NewCanvasView
     * @param canvasViewModel the CanvasViewModel to inject into the NewCanvasView
     * @param userDataAccessObject the NewCanvasUserDataAccessInterface to inject into the NewCanvasView
     * @return the NewCanvasView created for the provided input classes
     */
    public static MyCanvasesView create(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            CanvasViewModel canvasViewModel,
            NewCanvasUserDataAccessInterface userDataAccessObject) {

        final NewCanvasController newCanvasController = createNewCanvasUseCase(viewManagerModel, newCanvasViewModel,
                                                                   canvasViewModel, userDataAccessObject);
        return new MyCanvasesView(newCanvasViewModel, newCanvasController);

    }

    private static NewCanvasController createNewCanvasUseCase(
            ViewManagerModel viewManagerModel,
            NewCanvasViewModel newCanvasViewModel,
            CanvasViewModel canvasViewModel,
            NewCanvasUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        final NewCanvasOutputBoundary newCanvasOutputBoundary = new NewCanvasPresenter(viewManagerModel,
                                                                           canvasViewModel, newCanvasViewModel);
        final NewCanvasInputBoundary newCanvasInteractor = new NewCanvasInteractor(
                userDataAccessObject, newCanvasOutputBoundary);

        return new NewCanvasController(newCanvasInteractor);
    }
}
