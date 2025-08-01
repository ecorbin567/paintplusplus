package use_case.image.import_image;

/**
 * The output boundary for the Import Use Case.
 */
public interface ImportOutputBoundary {
    void present(ImportResponseModel response);
    void presentError(String message);
}
