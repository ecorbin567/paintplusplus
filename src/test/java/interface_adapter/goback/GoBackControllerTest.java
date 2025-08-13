package interface_adapter.goback;

import org.junit.jupiter.api.Test;
import use_case.goback.GoBackInputBoundary;
import use_case.goback.GoBackInputData;

import static org.junit.jupiter.api.Assertions.*;

class GoBackControllerTest {

    @Test
    void execute() {
        FakeGoBackInteractor interactor = new FakeGoBackInteractor();
        GoBackController controller = new GoBackController(interactor);

        String username = "alice";
        String password = "secret";
        String command  = "UNDO";

        controller.execute(username, password, command);

        assertTrue(interactor.called);
        assertNotNull(interactor.inputData);
        assertEquals(command, interactor.capturedCommand);
    }
    private static class FakeGoBackInteractor implements GoBackInputBoundary {
        boolean called;
        GoBackInputData inputData;
        String capturedCommand;

        @Override
        public void execute(GoBackInputData goBackInputData, String command) {
            called = true;
            inputData = goBackInputData;
            capturedCommand = command;
        }
    }
}
