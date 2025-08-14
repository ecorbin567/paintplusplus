package app.imagefactory;

import data_access.LocalImageLoader;
import entity.CanvasState;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.image.import_image.ImportController;
import interface_adapter.image.import_image.ImportPresenter;
import use_case.image.import_image.ImportInputBoundary;
import use_case.image.import_image.ImportInteractor;
import use_case.image.import_image.ImportOutputBoundary;

public final class ImportUseCaseFactory {
    private ImportUseCaseFactory() {
        // Unneded
    }

    /**
     * Factory function for creating the Import Use Case.
     *
     * @param canvasState      the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @param imageLoader      the ImageLoader
     * @return the ImportController created
     */
    public static ImportController create(CanvasState canvasState,
                                          DrawingViewModel drawingViewModel,
                                          LocalImageLoader imageLoader) {
        final ImportOutputBoundary presenter = new ImportPresenter(drawingViewModel);
        final ImportInputBoundary interactor = new ImportInteractor(imageLoader, presenter, canvasState);
        return new ImportController(interactor);
    }
}
