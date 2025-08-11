package interface_adapter.topmenu.canvassize;

import interface_adapter.canvas.DrawingViewModel;
import use_case.topmenu.canvassize.SizeOutputBoundary;
import use_case.topmenu.canvassize.SizeOutputData;

public class ResizeCanvasPresenter implements SizeOutputBoundary {
    private final DrawingViewModel drawingViewModel;

    public ResizeCanvasPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void setScale(SizeOutputData outputData) {
        double scale = outputData.getScale();
        this.drawingViewModel.setScale(scale);
    }
}
