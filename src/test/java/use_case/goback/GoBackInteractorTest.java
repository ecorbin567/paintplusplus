package use_case.goback;

import data_access.CanvasDataAccessInterface;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoBackInteractorTest {

    @Test
    void execute() {
        FakeCanvasDAO dao = new FakeCanvasDAO();
        FakePresenter presenter = new FakePresenter();
        GoBackInteractor interactor = new GoBackInteractor(dao, presenter);

        // prepare DAO return
        List<BufferedImage> stored = new ArrayList<>();
        stored.add(new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB));
        stored.add(new BufferedImage(6, 5, BufferedImage.TYPE_INT_ARGB));
        dao.toReturn = stored;

        String username = "alice";
        String command = "UNDO";
        String password = "alice";

        interactor.execute(new GoBackInputData(username, password), command);

        // DAO called with username
        assertTrue(dao.getAllCalled);
        assertEquals(username, dao.lastGetAllUser);

        // Presenter called with command + output data
        assertTrue(presenter.called);
        assertEquals(command, presenter.lastCommand);
        assertNotNull(presenter.lastData);
        assertEquals(username, presenter.lastData.getUsername());
        assertSame(stored, presenter.lastData.getUpdatedCanvases());
    }

    private static class FakeCanvasDAO implements CanvasDataAccessInterface {
        boolean getAllCalled = false;
        String lastGetAllUser = null;
        List<BufferedImage> toReturn = List.of();

        @Override
        public boolean saveCanvas(String username, BufferedImage image) { /* not used */
            return false;
        }

        @Override
        public List<BufferedImage> getAllCanvases(String username) {
            getAllCalled = true;
            lastGetAllUser = username;
            return toReturn;
        }
    }

    private static class FakePresenter implements GoBackOutputBoundary {
        boolean called = false;
        String lastCommand = null;
        GoBackOutputData lastData = null;

        @Override
        public void prepareSuccessView(String command, GoBackOutputData data) {
            called = true;
            lastCommand = command;
            lastData = data;
        }
    }
}
