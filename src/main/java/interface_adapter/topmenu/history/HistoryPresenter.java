package interface_adapter.topmenu.history;

import entity.Drawable;
import interface_adapter.canvas.DrawingViewModel;
import use_case.topmenu.history.HistoryOutputBoundary;
import use_case.topmenu.history.HistoryOutputData;
import java.util.Stack;

public class HistoryPresenter implements HistoryOutputBoundary {
    DrawingViewModel drawingViewModel;
    public HistoryPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void setDrawables(HistoryOutputData outputData) {
        Stack<Drawable> undoStack = outputData.getDrawableStack();
        this.drawingViewModel.setDrawables(undoStack);
    }

    @Override
    public void setCurrentDrawable(HistoryOutputData outputData) {
        Drawable drawable = outputData.getCurrentDrawable();
        this.drawingViewModel.setDrawable(drawable);
    }

    @Override
    public void setRepaintState(HistoryOutputData outputData) {
        boolean status = outputData.getStackEmpty();
        this.drawingViewModel.shouldRepaint(status);
    }
}
