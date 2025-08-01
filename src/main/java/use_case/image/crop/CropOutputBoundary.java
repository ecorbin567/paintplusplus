package use_case.image.crop;

/**
 * The output boundary for the Crop Use Case.
 */
public interface CropOutputBoundary {
    void present(CropResponseModel responseModel);
    void presentError(String error);
}
