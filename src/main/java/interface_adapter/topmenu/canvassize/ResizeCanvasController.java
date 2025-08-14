package interface_adapter.topmenu.canvassize;

import use_case.topmenu.canvassize.SizeInputBoundary;

public class ResizeCanvasController {
    SizeInputBoundary interactor;

    public ResizeCanvasController(SizeInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleZoomInButtonPress() {
        interactor.zoomIn();
    }

    public void handleZoomOutButtonPress() {
        interactor.zoomOut();
    }
}
