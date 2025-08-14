package use_case.image.import_image;

import java.io.File;
import java.io.IOException;
import java.util.List;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;

/**
 * The Import Interactor.
 */
public class ImportInteractor implements ImportInputBoundary {

    private final ImportGateway gateway;
    private final ImportOutputBoundary presenter;
    private final CanvasState canvasState;
    private final ActionHistory actionHistory;

    public ImportInteractor(ImportGateway gateway,
                            ImportOutputBoundary presenter,
                            CanvasState canvasState) {
        this.gateway = gateway;
        this.presenter = presenter;
        this.canvasState = canvasState;
        this.actionHistory = canvasState.getActionHistory();
    }

    @Override
    public void execute(ImportRequestModel request) {
        File file = request.file();
        List<Image> importedImages = this.canvasState.getImportedImages();

        try {
            Image image = gateway.loadImage(file);

            actionHistory.push(image);
            importedImages.add(image);
            this.canvasState.setCurrentImage(image);

            ImportResponseModel response = new ImportResponseModel(importedImages);
            presenter.present(response);
        }
        catch (IOException e) {
            presenter.presentError("Failed to import image: " + e.getMessage());
        }
    }
}
