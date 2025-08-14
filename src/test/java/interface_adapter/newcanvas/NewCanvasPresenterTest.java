package interface_adapter.newcanvas;

import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.newcanvas.NewCanvasOutputData;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewCanvasPresenterTest {

    @Test
    void prepareSuccessView_withImportedCanvas_firesImportAndClearHistory_andNavigates() {
        ViewManagerModel viewManager = new ViewManagerModel();
        CanvasViewModel canvasVM = new CanvasViewModel();
        NewCanvasViewModel newCanvasVM = new NewCanvasViewModel();
        SignupViewModel signupVM = new SignupViewModel();

        NewCanvasPresenter presenter =
                new NewCanvasPresenter(viewManager, canvasVM, newCanvasVM, signupVM);

        // capture CanvasViewModel property change events
        List<String> events = new ArrayList<>();
        PropertyChangeListener l = evt -> events.add(evt.getPropertyName());
        canvasVM.addPropertyChangeListener(l);

        BufferedImage img = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
        NewCanvasOutputData output = new NewCanvasOutputData("alice", img);

        presenter.prepareSuccessView(output);

        assertEquals(canvasVM.getViewName(), viewManager.getState(),
                "Presenter should navigate to the Canvas view");
        assertTrue(events.contains("import"),
                "CanvasViewModel should fire an 'import' event when an initial image is provided");
        assertTrue(events.contains("clearhistory"),
                "CanvasViewModel should fire 'clearhistory' after switching to the canvas");
    }

    @Test
    void prepareSuccessView_withoutImportedCanvas_skipsImport_butClearsHistory_andNavigates() {
        ViewManagerModel viewManager = new ViewManagerModel();
        CanvasViewModel canvasVM = new CanvasViewModel();
        NewCanvasViewModel newCanvasVM = new NewCanvasViewModel();
        SignupViewModel signupVM = new SignupViewModel();

        NewCanvasPresenter presenter =
                new NewCanvasPresenter(viewManager, canvasVM, newCanvasVM, signupVM);

        List<String> events = new ArrayList<>();
        PropertyChangeListener l = evt -> events.add(evt.getPropertyName());
        canvasVM.addPropertyChangeListener(l);

        NewCanvasOutputData output = new NewCanvasOutputData("bob", null);

        presenter.prepareSuccessView(output);

        assertEquals(canvasVM.getViewName(), viewManager.getState());
        assertFalse(events.contains("import"),
                "No 'import' event should be fired when no initial image is provided");
        assertTrue(events.contains("clearhistory"),
                "'clearhistory' should still be fired");
    }

    @Test
    void switchToSignupView_navigatesToSignup() {
        ViewManagerModel viewManager = new ViewManagerModel();
        CanvasViewModel canvasVM = new CanvasViewModel();
        NewCanvasViewModel newCanvasVM = new NewCanvasViewModel();
        SignupViewModel signupVM = new SignupViewModel();

        NewCanvasPresenter presenter =
                new NewCanvasPresenter(viewManager, canvasVM, newCanvasVM, signupVM);

        presenter.switchToSignupView();

        assertEquals(signupVM.getViewName(), viewManager.getState(),
                "Presenter should navigate to the Signup view");
    }
}
