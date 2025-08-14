package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SignUpPresenterTest {

    @Test
    void prepareSuccessView_updatesLoginVM_andNavigates() {
        ViewManagerModel viewManager = new ViewManagerModel();
        SignupViewModel signupVM = new SignupViewModel();
        LoginViewModel loginVM = new LoginViewModel();

        SignupPresenter presenter =
                new SignupPresenter(viewManager, signupVM, loginVM);

        // capture property changes on the LoginViewModel
        List<String> loginEvents = new ArrayList<>();
        PropertyChangeListener l = evt -> loginEvents.add(evt.getPropertyName());
        loginVM.addPropertyChangeListener(l);

        String username = "neo";
        presenter.prepareSuccessView(new SignupOutputData(username, /*useCaseFailed*/ false));

        // login VM state updated
        assertEquals(username, loginVM.getState().getUsername(),
                "LoginViewModel state should receive the signed-up username");
        assertFalse(loginEvents.isEmpty(),
                "prepareSuccessView should fire a property change on LoginViewModel");

        // navigation goes to Login view
        assertEquals(loginVM.getViewName(), viewManager.getState(),
                "Presenter should navigate to the Login view");
    }

    @Test
    void prepareFailView_setsError_onSignupVM_only() {
        ViewManagerModel viewManager = new ViewManagerModel();
        SignupViewModel signupVM = new SignupViewModel();
        LoginViewModel loginVM = new LoginViewModel();

        SignupPresenter presenter =
                new SignupPresenter(viewManager, signupVM, loginVM);

        List<String> signupEvents = new ArrayList<>();
        signupVM.addPropertyChangeListener(evt -> signupEvents.add(evt.getPropertyName()));

        String msg = "username already taken";
        presenter.prepareFailView(msg);

        assertEquals(msg, signupVM.getState().getUsernameError(),
                "Signup error should be pushed into SignupViewModel state");
        assertFalse(signupEvents.isEmpty(),
                "prepareFailView should notify listeners on SignupViewModel");

        // No navigation expected on failure (state remains whatever it was; often null)
        // We simply assert it did not switch to Login.
        assertNotEquals(loginVM.getViewName(), viewManager.getState(),
                "Failure should not navigate to the Login view");
    }

    @Test
    void switchToLoginView_navigates() {
        ViewManagerModel viewManager = new ViewManagerModel();
        SignupViewModel signupVM = new SignupViewModel();
        LoginViewModel loginVM = new LoginViewModel();

        SignupPresenter presenter =
                new SignupPresenter(viewManager, signupVM, loginVM);

        presenter.switchToLoginView();

        assertEquals(loginVM.getViewName(), viewManager.getState(),
                "switchToLoginView should navigate to the Login view");
    }
}
