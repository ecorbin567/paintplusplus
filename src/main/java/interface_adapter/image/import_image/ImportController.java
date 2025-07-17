package interface_adapter.image.import_image;

import use_case.image.import_image.ImportInputBoundary;

public class ImportController {
    private final ImportInputBoundary interactor;

    public ImportController(ImportInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {}

}
