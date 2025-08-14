package interface_adapter.signup;

import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpControllerTest {

    /** Simple spy interactor to capture calls and inputs. */
    private static class SpyInteractor implements SignupInputBoundary {
        SignupInputData last;
        int executeCalls = 0;
        int switchCalls = 0;

        @Override
        public void execute(SignupInputData inputData) {
            executeCalls++;
            last = inputData;
        }

        @Override
        public void switchToLoginView() {
            switchCalls++;
        }
    }

    @Test
    void execute_forwardsInputsUnchanged_toInteractor() {
        SpyInteractor spy = new SpyInteractor();
        SignupController controller = new SignupController(spy);

        String u = "  User  ";
        String p1 = "  p1  ";
        String p2 = "  p2  ";

        controller.execute(u, p1, p2);

        assertEquals(1, spy.executeCalls, "execute() should be called exactly once");
        assertNotNull(spy.last, "interactor should receive a SignupInputData");
        assertEquals(u,  spy.last.getUsername(),       "username should be forwarded unchanged");
        assertEquals(p1, spy.last.getPassword(),       "password should be forwarded unchanged");
        assertEquals(p2, spy.last.getRepeatPassword(), "repeatPassword should be forwarded unchanged");

        assertEquals(0, spy.switchCalls, "execute() must not call switchToLoginView()");
    }

    @Test
    void switchToLoginView_forwardsToInteractor_only() {
        SpyInteractor spy = new SpyInteractor();
        SignupController controller = new SignupController(spy);

        controller.switchToLoginView();

        assertEquals(1, spy.switchCalls, "switchToLoginView() should be called exactly once");
        assertEquals(0, spy.executeCalls, "switchToLoginView() must not trigger execute()");
        assertNull(spy.last, "no SignupInputData should be sent when switching views");
    }
}
