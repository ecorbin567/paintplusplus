package use_case.image.import_image;

import entity.Image;
import entity.ActionHistory;

import java.io.File;
import java.io.IOException;

public class ImportInteractor implements ImportInputBoundary {

    private final ImportGateway gateway;
    private final ImportOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public ImportInteractor(ImportGateway gateway, ImportOutputBoundary presenter, ActionHistory actionHistory) {
        this.gateway = gateway;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(ImportRequestModel request) {
        File file = request.getFile();

        try {
            Image image = gateway.loadImage(file);

            actionHistory.push(image);

            ImportResponseModel response = new ImportResponseModel(image);
            presenter.present(response);
        } catch (IOException e) {
            presenter.presentError("Failed to import image: " + e.getMessage());
        }
    }
}
