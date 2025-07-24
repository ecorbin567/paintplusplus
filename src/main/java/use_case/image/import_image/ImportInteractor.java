package use_case.image.import_image;

import entity.Image;

import java.io.File;
import java.io.IOException;

public class ImportInteractor implements ImportInputBoundary {
    private final ImportGateway gateway;
    private final ImportOutputBoundary presenter;

    public ImportInteractor(ImportGateway gateway, ImportOutputBoundary presenter) {
        this.gateway = gateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(ImportRequestModel request) {
        File file = request.getFile();

        try {
            Image image = gateway.loadImage(file);
            ImportResponseModel response = new ImportResponseModel(image);
            presenter.present(response);
        } catch (IOException e) {
            presenter.presentError("Failed to import image: " + e.getMessage());
        }
    }
}
