package interface_adapter.login;

import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private static class SpyInteractor implements LoginInputBoundary {
        LoginInputData last;
        int calls = 0;

        @Override
        public void execute(LoginInputData inputData) {
            calls++;
            last = inputData;
        }
    }

    @Test
    void execute_forwardsUsernameAndPassword() {
        SpyInteractor spy = new SpyInteractor();
        LoginController controller = new LoginController(spy);

        controller.execute("alice", "s3cr3t!");

        assertEquals(1, spy.calls, "Interactor should be called once");
        assertNotNull(spy.last, "Request should be created and passed");
        assertEquals("alice", spy.last.getUsername());
        assertEquals("s3cr3t!", spy.last.getPassword());
    }

    @Test
    void execute_multipleCalls_lastRequestIsLatest() {
        SpyInteractor spy = new SpyInteractor();
        LoginController controller = new LoginController(spy);

        controller.execute("user1", "p1");
        controller.execute("user2", "p2");

        assertEquals(2, spy.calls, "Interactor should be called twice");
        assertEquals("user2", spy.last.getUsername());
        assertEquals("p2", spy.last.getPassword());
    }

    @Test
    void execute_preservesWhitespaceAndCase() {
        SpyInteractor spy = new SpyInteractor();
        LoginController controller = new LoginController(spy);

        controller.execute("  Alice  ", "  Pass Word  ");

        assertEquals(1, spy.calls);
        assertEquals("  Alice  ", spy.last.getUsername(), "Controller should not trim/alter username");
        assertEquals("  Pass Word  ", spy.last.getPassword(), "Controller should not trim/alter password");
    }
}
