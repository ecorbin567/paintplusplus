package interface_adapter.image.import_image;

import java.io.File;

import use_case.image.import_image.ImportInputBoundary;
import use_case.image.import_image.ImportRequestModel;

/**
 * The controller for the Import Use Case.
 */
public class ImportController {
    private final ImportInputBoundary interactor;

    public ImportController(ImportInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(File selectedFile) {
        final ImportRequestModel request = new ImportRequestModel(selectedFile);
        interactor.execute(request);
    }

}
