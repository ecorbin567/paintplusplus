package interface_adapter.topmenu.history;

import entity.CutRecord;
import entity.Drawable;
import interface_adapter.canvas.DrawingViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.topmenu.history.HistoryOutputData;

import java.awt.*;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class HistoryPresenterTest {

    private DrawingViewModel drawingVM;
    private HistoryPresenter presenter;

    @BeforeEach
    void setup() {
        drawingVM = new DrawingViewModel();
        presenter = new HistoryPresenter(drawingVM);
    }

    @Test
    void setDrawables_updatesViewModelStack() {
        Stack<Drawable> stack = new Stack<>();
        stack.push(new CutRecord(new Rectangle(1, 2, 3, 4)));

        HistoryOutputData out = new HistoryOutputData(stack, true, null);

        presenter.setDrawables(out);

        assertSame(stack, drawingVM.getDrawables(),
                "Presenter should pass the undo stack to the DrawingViewModel");
    }

    @Test
    void setCurrentDrawable_updatesViewModelCurrent() {
        Drawable current = new CutRecord(new Rectangle(5, 6, 7, 8));
        HistoryOutputData out = new HistoryOutputData(new Stack<>(), true, current);

        presenter.setCurrentDrawable(out);

        assertSame(current, drawingVM.getDrawable(),
                "Presenter should set the current drawable on the DrawingViewModel");
    }

    @Test
    void setRepaintState_updatesRepaintFlag() {
        // When stackEmpty = true
        presenter.setRepaintState(new HistoryOutputData(new Stack<>(), true, null));
        assertTrue(drawingVM.getRepaintState(),
                "Repaint flag should mirror the 'stackEmpty' boolean from output data");

        // When stackEmpty = false
        presenter.setRepaintState(new HistoryOutputData(new Stack<>(), false, null));
        assertFalse(drawingVM.getRepaintState(),
                "Repaint flag should update when 'stackEmpty' changes");
    }

    @Test
    void getCurrentDrawable_readsFromViewModel() {
        Drawable d = new CutRecord(new Rectangle(0, 0, 10, 10));
        drawingVM.setDrawable(d);

        assertSame(d, presenter.getCurrentDrawable(),
                "getCurrentDrawable should delegate to DrawingViewModel");
    }
}
