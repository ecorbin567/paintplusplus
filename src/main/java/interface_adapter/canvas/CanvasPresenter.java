package interface_adapter.canvas;

import entity.Drawable;
import use_case.mouseui.MouseUIOutputBoundary;
import use_case.mouseui.MouseUIOutputData;
import use_case.topmenu.TopMenuOutputBoundary;
import use_case.topmenu.TopMenuOutputData;

import java.util.Stack;

public class CanvasPresenter implements TopMenuOutputBoundary, MouseUIOutputBoundary {
    private final CanvasViewModel canvasViewModel;

    public CanvasPresenter(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void setRepaintState(TopMenuOutputData outputData) {
        boolean status = outputData.isStackEmpty();
        this.canvasViewModel.shouldRepaint(status);
    }

    @Override
    public void setDrawables(TopMenuOutputData outputData) {
        Stack<Drawable> drawables = outputData.getDrawables();
        this.canvasViewModel.setDrawables(drawables);
    }

    @Override
    public void setRepaintState(MouseUIOutputData outputData) {
        boolean state = outputData.getState();
        this.canvasViewModel.shouldRepaint(state);
    }

    @Override
    public void setDrawableState(MouseUIOutputData outputData) {
        Stack<Drawable> drawables = outputData.getDrawables();
        this.canvasViewModel.setDrawables(drawables);
    }

    @Override
    public void setCurrentDrawable(MouseUIOutputData outputData) {
        Drawable currentDrawable = outputData.getDrawable();
        this.canvasViewModel.setDrawable(currentDrawable);
    }

    @Override
    public void setScale(TopMenuOutputData outputData) {
        double scale = canvasViewModel.getScale();
        this.canvasViewModel.setScale(scale);
    }
}
