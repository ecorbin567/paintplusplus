package use_case.image.import_image;

/**
 * The output boundary for the Import Use Case.
 */
public interface ImportOutputBoundary {

    /**
     * Presents a successful import to the UI layer.
     * @param response the import response containing the loaded image and data.
     */
    void present(ImportResponseModel response);

    /**
     * Presents an import failure message to the UI layer.
     * @param message and error message why the import failed.
     */
    void presentError(String message);
}
