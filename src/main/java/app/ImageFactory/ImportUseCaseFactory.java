package app.ImageFactory;

import data_access.LocalImageLoader;
import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.import_image.ImportController;
import interface_adapter.image.import_image.ImportPresenter;
import use_case.image.import_image.ImportInputBoundary;
import use_case.image.import_image.ImportInteractor;
import use_case.image.import_image.ImportOutputBoundary;

public class ImportUseCaseFactory {
    public static ImportController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel,
                                          LocalImageLoader imageLoader) {
        ImportOutputBoundary presenter = new ImportPresenter(drawingViewModel);
        ImportInputBoundary interactor = new ImportInteractor(imageLoader, presenter, canvasState);
        return new ImportController(interactor);
    }
}
