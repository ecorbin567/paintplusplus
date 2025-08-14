package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.newcanvas.NewCanvasViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginOutputData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginPresenterTest {

    @Test
    void prepareSuccessView_updatesModels_andNavigates() {
        // Real models (POJOs with PropertyChangeSupport)
        ViewManagerModel viewManager = new ViewManagerModel();
        NewCanvasViewModel canvasVM = new NewCanvasViewModel();
        LoginViewModel loginVM = new LoginViewModel();
        DrawingViewModel drawingVM = new DrawingViewModel();
        GoBackViewModel goBackVM = new GoBackViewModel();

        LoginPresenter presenter = new LoginPresenter(
                viewManager, canvasVM, loginVM, drawingVM, goBackVM
        );

        String username = "alice";
        List<BufferedImage> canvases = new ArrayList<>();
        canvases.add(img(10, 10));

        // useCaseFailed = false for success path
        LoginOutputData response = new LoginOutputData(username, false, canvases);

        presenter.prepareSuccessView(response);

        // Drawing VM gets the username
        assertEquals(username, drawingVM.getCurrentUser(),
                "DrawingViewModel should be updated with username");

        // GoBack VM mirrors username for later use
        assertEquals(username, goBackVM.getState().getUsername(),
                "GoBackViewModel should store the username");

        // Canvas selector is populated
        assertEquals(canvases, canvasVM.getState().getCanvases(),
                "Canvas list should be pushed into NewCanvasViewModel state");

        // Navigation to canvas screen
        assertEquals(canvasVM.getViewName(), viewManager.getState(),
                "ViewManager should navigate to the canvas selection view");
    }

    @Test
    void prepareFailView_setsError_onLoginViewModel() {
        ViewManagerModel viewManager = new ViewManagerModel();
        NewCanvasViewModel canvasVM = new NewCanvasViewModel();
        LoginViewModel loginVM = new LoginViewModel();
        DrawingViewModel drawingVM = new DrawingViewModel();
        GoBackViewModel goBackVM = new GoBackViewModel();

        LoginPresenter presenter = new LoginPresenter(
                viewManager, canvasVM, loginVM, drawingVM, goBackVM
        );

        String error = "Invalid credentials";
        presenter.prepareFailView(error);

        assertEquals(error, loginVM.getState().getLoginError(),
                "LoginViewModel should hold the error message on failure");
    }

    private static BufferedImage img(int w, int h) {
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }
}
