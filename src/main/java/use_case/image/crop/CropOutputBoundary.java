package use_case.image.crop;

/**
 * The output boundary for the Crop Use Case.
 */
public interface CropOutputBoundary {

    /**
     * Presents a crop result to the caller.
     *
     * @param responseModel the result of the crop operation
     */
    void present(CropResponseModel responseModel);

    /**
     * Presents a crop failure to the caller.
     *
     * @param error an explanation of the crop failure.
     */
    void presentError(String error);

}
