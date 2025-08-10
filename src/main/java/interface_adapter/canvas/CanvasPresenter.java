package interface_adapter.canvas;

import entity.Drawable;
import use_case.mouseui.MouseUIOutputBoundary;
import use_case.mouseui.MouseUIOutputData;
import use_case.topmenu.TopMenuOutputBoundary;
import use_case.topmenu.TopMenuOutputData;

import java.util.Stack;

public class CanvasPresenter implements TopMenuOutputBoundary, MouseUIOutputBoundary {
    private final DrawingViewModel drawingViewModel;

    public CanvasPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void setRepaintState(TopMenuOutputData outputData) {
        boolean status = outputData.isStackEmpty();
        this.drawingViewModel.shouldRepaint(status);
    }

    @Override
    public void setDrawables(TopMenuOutputData outputData) {
        Stack<Drawable> drawables = outputData.getDrawables();
        System.out.println("Presenter");
        System.out.println(drawables.size());
        this.drawingViewModel.setDrawables(drawables);
    }

    @Override
    public void setRepaintState(MouseUIOutputData outputData) {
        boolean state = outputData.getState();
        this.drawingViewModel.shouldRepaint(state);
    }

    @Override
    public void setDrawableState(MouseUIOutputData outputData) {
        System.out.println("Presenter");
        Stack<Drawable> drawables = outputData.getDrawables();
        System.out.println(drawables.size());
        this.drawingViewModel.setDrawables(drawables);
    }

    @Override
    public void setCurrentDrawable(MouseUIOutputData outputData) {
        Drawable currentDrawable = outputData.getDrawable();
        this.drawingViewModel.setDrawable(currentDrawable);
    }

    @Override
    public void setScale(TopMenuOutputData outputData) {
        double scale = outputData.getScale();
        this.drawingViewModel.setScale(scale);
    }
}
