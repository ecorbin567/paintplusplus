package use_case.topmenu.canvassize;
import entity.CanvasState;

public class SizeInteractor implements SizeInputBoundary{

    private final CanvasState canvasState;
    private final SizeOutputBoundary presenter;

    public SizeInteractor(CanvasState canvasState,
                             SizeOutputBoundary presenter) {
        this.canvasState = canvasState;
        this.presenter = presenter;
    }

    @Override
    public void zoomIn() {
        double scale = canvasState.getScale() + 0.1;
        setScaleOutput(scale);
    }

    @Override
    public void zoomOut() {
        double scale = canvasState.getScale() - 0.1;
        setScaleOutput(scale);
    }

    private void setScaleOutput(double scale) {
        canvasState.setScale(scale);
        SizeOutputData outputData = new SizeOutputData(scale);
        presenter.setScale(outputData);
    }
}
