package use_case.image.rotate;

/**
 * The Output Boundary for the Rotate use case.
 */
public interface RotateOutputBoundary {

    /**
     * Presents a successful rotate result.
     * @param responseModel the rotate response input data.
     */
    void present(RotateResponseModel responseModel);

    /**
     * Presents an error indicating the rotate operation failure.
     * @param error an error message describing the failure.
     */
    void presentError(String error);
}
