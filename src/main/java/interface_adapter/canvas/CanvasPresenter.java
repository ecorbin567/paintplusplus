package interface_adapter.canvas;

import entity.Drawable;
import use_case.mouseui.MouseUIOutputBoundary;
import use_case.mouseui.MouseUIOutputData;

import java.util.Stack;

public class CanvasPresenter implements MouseUIOutputBoundary {
    private final DrawingViewModel drawingViewModel;

    public CanvasPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void setRepaintState(MouseUIOutputData outputData) {
        boolean state = outputData.getState();
        this.drawingViewModel.shouldRepaint(state);
    }

    @Override
    public void setDrawableState(MouseUIOutputData outputData) {
        Stack<Drawable> drawables = outputData.getDrawables();
        this.drawingViewModel.setDrawables(drawables);
    }

    @Override
    public void setCurrentDrawable(MouseUIOutputData outputData) {
        Drawable currentDrawable = outputData.getDrawable();
        this.drawingViewModel.setDrawable(currentDrawable);
    }
}
