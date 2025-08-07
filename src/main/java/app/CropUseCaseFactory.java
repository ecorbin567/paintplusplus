package app;

public class CropUseCaseFactory {
    public static CropController create(CanvasView canvas, ActionHistory actionHistory) {
        CropOutputBoundary presenter = new CropPresenter(canvas);
        CropInputBoundary interactor = new CropInteractor(canvas, presenter, actionHistory);
        return new CropController(interactor);
    }
}