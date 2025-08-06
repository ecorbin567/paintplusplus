package interface_adapter.canvas;

import entity.Drawable;
import use_case.topmenu.TopMenuOutputBoundary;
import use_case.topmenu.TopMenuOutputData;

import java.util.Stack;

public class CanvasPresenter implements TopMenuOutputBoundary {
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

    public void prepareSuccessSave(TopMenuOutputData outputData) {

    }

    public void prepareFailedSave(){

    }


}
