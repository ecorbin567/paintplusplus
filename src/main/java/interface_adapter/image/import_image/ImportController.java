package interface_adapter.image.import_image;

import use_case.image.import_image.ImportInputBoundary;
import use_case.image.import_image.ImportRequestModel;

import java.io.File;

public class ImportController {
    private final ImportInputBoundary interactor;

    public ImportController(ImportInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(File selectedFile) {
        ImportRequestModel request = new ImportRequestModel(selectedFile);
        interactor.execute(request);
    }

}
