package use_case.image.crop;

public interface CropOutputBoundary {
    void present(CropResponseModel responseModel);
    void presentError(String error);

}
