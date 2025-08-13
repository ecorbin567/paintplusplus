package use_case.newcanvas;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewCanvasInteractorTest {

    @Test
    void execute() {
        FakeDAO dao = new FakeDAO();
        FakePresenter presenter = new FakePresenter();
        NewCanvasInteractor interactor = new NewCanvasInteractor(dao, presenter);

        String username = "alice";
        interactor.execute(new NewCanvasInputData(username, "pw", null));

        assertTrue(presenter.successCalled);
        assertNotNull(presenter.lastOutput);
        assertEquals(username, presenter.lastOutput.getUsername());
    }

    @Test
    void executeImportExistingCanvas() {
        FakeDAO dao = new FakeDAO();
        FakePresenter presenter = new FakePresenter();
        NewCanvasInteractor interactor = new NewCanvasInteractor(dao, presenter);

        String username = "bob";
        BufferedImage imported = new BufferedImage(7, 5, BufferedImage.TYPE_INT_ARGB);
        interactor.executeImportExistingCanvas(new NewCanvasInputData(username, "pw", imported));

        assertTrue(presenter.successCalled);
        assertNotNull(presenter.lastOutput);
        assertEquals(username, presenter.lastOutput.getUsername());
    }

    @Test
    void switchToSignupView() {
        FakeDAO dao = new FakeDAO();
        FakePresenter presenter = new FakePresenter();
        NewCanvasInteractor interactor = new NewCanvasInteractor(dao, presenter);

        interactor.switchToSignupView();

        assertTrue(presenter.switchCalled);
    }

    @Test
    void getUserCanvases() {
        FakeDAO dao = new FakeDAO();
        FakePresenter presenter = new FakePresenter();
        NewCanvasInteractor interactor = new NewCanvasInteractor(dao, presenter);

        List<BufferedImage> stored = new ArrayList<>();
        stored.add(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB));
        stored.add(new BufferedImage(4, 2, BufferedImage.TYPE_INT_ARGB));
        dao.toReturn = stored;

        String username = "carol";
        List<BufferedImage> result =
                interactor.getUserCanvases(new NewCanvasInputData(username, "pw", null));

        assertTrue(dao.getAllCalled);
        assertEquals(username, dao.lastUsername);
        assertSame(stored, result);
    }

    private static class FakeDAO implements NewCanvasUserDataAccessInterface {
        boolean getAllCalled = false;
        String lastUsername = null;
        List<BufferedImage> toReturn = List.of();

        @Override
        public boolean saveCanvas(String username, BufferedImage image) {
            return false;
        }

        @Override
        public List<BufferedImage> getAllCanvases(String username) {
            getAllCalled = true;
            lastUsername = username;
            return toReturn;
        }
    }

    private static class FakePresenter implements NewCanvasOutputBoundary {
        boolean successCalled = false;
        boolean switchCalled = false;
        NewCanvasOutputData lastOutput = null;

        @Override
        public void prepareSuccessView(NewCanvasOutputData outputData) {
            successCalled = true;
            lastOutput = outputData;
        }

        @Override
        public void switchToSignupView() {
            switchCalled = true;
        }
    }
}
