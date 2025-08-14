package use_case.topmenu.history;

import entity.CanvasState;
import entity.Drawable;
import entity.StrokeRecord;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.topmenu.history.HistoryPresenter;
import org.junit.jupiter.api.Test;
import use_case.mouseui.MouseUIInputData;
import use_case.mouseui.MouseUIOutputBoundary;
import use_case.mouseui.MouseUIUseInteractor;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HistoryInteractorTest {
    CanvasState canvasState = new CanvasState();
    DrawingViewModel drawingViewModel = new DrawingViewModel();
    MouseUIOutputBoundary mouseUIOutputBoundary = new CanvasPresenter(drawingViewModel);
    MouseUIUseInteractor mouseInteractor = new MouseUIUseInteractor(
            canvasState, mouseUIOutputBoundary
    );
    HistoryOutputBoundary presenter = new HistoryPresenter(drawingViewModel);

    @Test
    void undoDrawable() {
        mouseInteractor.setCurrentState(new StrokeRecord(Color.BLACK, 3f));
        mouseInteractor.mouseIsPressed(new MouseUIInputData(new Point(10, 10)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(11, 11)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(12, 12)));
        mouseInteractor.mouseIsReleased(new MouseUIInputData(new Point(13, 13)));
        canvasState.setActionHistory(mouseInteractor.getActionHistory());
        HistoryInteractor historyInteractor = new HistoryInteractor(canvasState, presenter);
        Drawable oldDrawable = presenter.getCurrentDrawable();
        historyInteractor.undoDrawable();
        Drawable newDrawable = presenter.getCurrentDrawable();
        assertNotEquals(oldDrawable, newDrawable);
    }

    @Test
    void redoDrawable() {
        mouseInteractor.setCurrentState(new StrokeRecord(Color.BLACK, 3f));
        mouseInteractor.mouseIsPressed(new MouseUIInputData(new Point(10, 10)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(11, 11)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(12, 12)));
        mouseInteractor.mouseIsReleased(new MouseUIInputData(new Point(13, 13)));
        canvasState.setActionHistory(mouseInteractor.getActionHistory());
        HistoryInteractor historyInteractor = new HistoryInteractor(canvasState, presenter);
        historyInteractor.undoDrawable();
        Drawable oldDrawable = presenter.getCurrentDrawable();
        historyInteractor.redoDrawable();
        Drawable newDrawable = presenter.getCurrentDrawable();
        assertNotEquals(oldDrawable, newDrawable);
    }
}