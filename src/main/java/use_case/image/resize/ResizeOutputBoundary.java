package use_case.image.resize;

/**
 * The Output Boundary for the Resize use case.
 */
public interface ResizeOutputBoundary {

    /**
     * Presents a successful resize result.
     * @param responseModel the resize response input data.
     */
    void present(ResizeResponseModel responseModel);

    /**
     * Presents and error indicating the resize operation failure.
     * @param error an error message describing the failure.
     */
    void presentError(String error);
}
