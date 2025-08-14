package interface_adapter.goback;

import interface_adapter.ViewManagerModel;
import interface_adapter.newcanvas.NewCanvasState;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.goback.GoBackOutputData;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/** Tests for GoBackPresenter without external mocking libs. */
class GoBackPresenterTest {

    // --- Minimal test doubles to capture calls --------------------------------

    static class TestViewManagerModel extends ViewManagerModel {
        String lastState;
        int fireCount = 0;

        @Override
        public void setState(String state) {
            this.lastState = state;
        }

        @Override
        public void firePropertyChanged() {
            fireCount++;
        }
    }

    static class TestNewCanvasViewModel extends NewCanvasViewModel {
        private final NewCanvasState backing = new NewCanvasState();
        int fireCount = 0;
        List<BufferedImage> lastSetCanvases;

        @Override
        public NewCanvasState getState() {
            return backing;
        }

        @Override
        public void setState(NewCanvasState state) {
            // store as-is; in the real class this would replace internal state
        }

        @Override
        public void setCanvases(List<BufferedImage> canvases) {
            lastSetCanvases = canvases;
        }

        @Override
        public void firePropertyChanged() {
            fireCount++;
        }

        @Override
        public String getViewName() {
            return "new-canvas"; // any stable name is fine for the assertion
        }
    }

    static class TestSignupViewModel extends SignupViewModel {
        private final SignupState backing = new SignupState();
        int fireCount = 0;

        @Override
        public SignupState getState() {
            return backing;
        }

        @Override
        public void setState(SignupState state) {
            // store as-is; presenter just echoes current state
        }

        @Override
        public void firePropertyChanged() {
            fireCount++;
        }

        @Override
        public String getViewName() {
            return "signup";
        }
    }

    // --- Tests ----------------------------------------------------------------

    @Test
    void prepareSuccessView_logOut_switchesToSignup_andFiresEvents() {
        TestViewManagerModel vmm = new TestViewManagerModel();
        TestNewCanvasViewModel nvm = new TestNewCanvasViewModel();
        TestSignupViewModel svm = new TestSignupViewModel();

        GoBackPresenter presenter = new GoBackPresenter(vmm, nvm, svm);

        // For the logOut path, the presenter never reads from GoBackOutputData, so null is fine.
        presenter.prepareSuccessView("logOut", null);

        // View should switch to signup
        assertEquals("signup", vmm.lastState, "ViewManager should switch to Signup view");

        // Both presenters should have fired change events once
        assertEquals(1, svm.fireCount, "SignupViewModel should notify listeners");
        assertEquals(1, vmm.fireCount, "ViewManagerModel should notify listeners");
    }

    @Test
    void prepareSuccessView_goBack_updatesCanvases_andSwitchesView() {
        TestViewManagerModel vmm = new TestViewManagerModel();
        TestNewCanvasViewModel nvm = new TestNewCanvasViewModel();
        TestSignupViewModel svm = new TestSignupViewModel();
        GoBackPresenter presenter = new GoBackPresenter(vmm, nvm, svm);

        // Provide both username and image list as required by the constructor
        List<BufferedImage> canvases = new ArrayList<>();
        GoBackOutputData data = new GoBackOutputData("alice", canvases);

        presenter.prepareSuccessView("goBack", data);

        assertSame(canvases, nvm.lastSetCanvases, "Canvases should be forwarded to NewCanvasViewModel");
        assertEquals(1, nvm.fireCount);
        assertEquals(nvm.getViewName(), vmm.lastState);
        assertEquals(1, vmm.fireCount);
    }
}
