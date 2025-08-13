package use_case.userdatabase;

import data_access.SupabaseCanvasRepository;
import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.newcanvas.NewCanvasPresenter;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.newcanvas.*;

import static org.junit.jupiter.api.Assertions.*;

public class NewCanvasInteractorTest {
    final String USERNAME = "JaneDoe12345";
    final String PASSWORD = "password";
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    final CanvasViewModel canvasViewModel = new CanvasViewModel();
    final NewCanvasViewModel newCanvasViewModel = new NewCanvasViewModel();
    final SignupViewModel signupViewModel = new SignupViewModel();
    final NewCanvasUserDataAccessInterface canvasDataAccessObject = new SupabaseCanvasRepository();

    final NewCanvasOutputBoundary newCanvasOutputBoundary;
    final NewCanvasInputBoundary newCanvasInteractor;

    public NewCanvasInteractorTest() {
        this.newCanvasOutputBoundary = new NewCanvasPresenter(viewManagerModel,
                canvasViewModel, newCanvasViewModel,
                signupViewModel);

        this.newCanvasInteractor = new NewCanvasInteractor(
                canvasDataAccessObject, newCanvasOutputBoundary);
    }

    @Test
    public void testExecuteUseCase() {
        NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                USERNAME,
                PASSWORD,
                null
        );
        newCanvasInteractor.execute(newCanvasInputData);

        assertNotNull(canvasViewModel.getState().getInitialImportedImage());

    }

    @Test
    public void testExecuteImportUseCase() {
        /*
        Importing a previous canvas
         */

        NewCanvasInputData newCanvasInputData = new NewCanvasInputData(
                USERNAME,
                PASSWORD,
                null
        );
        newCanvasInteractor.execute(newCanvasInputData);

        assertNotNull(canvasViewModel.getState().getInitialImportedImage());

    }
}
