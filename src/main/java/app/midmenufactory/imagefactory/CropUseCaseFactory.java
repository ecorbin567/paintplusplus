package app.midmenufactory.imagefactory;

import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.midmenu.image.crop.CropController;
import interface_adapter.midmenu.image.crop.CropPresenter;
import use_case.image.crop.CropInputBoundary;
import use_case.image.crop.CropInteractor;
import use_case.image.crop.CropOutputBoundary;

public class CropUseCaseFactory {
    public static CropController create(CanvasState canvasState, DrawingViewModel drawingViewModel) {
        CropOutputBoundary presenter = new CropPresenter(drawingViewModel);
        CropInputBoundary interactor = new CropInteractor(canvasState, presenter);
        return new CropController(interactor);
    }
}