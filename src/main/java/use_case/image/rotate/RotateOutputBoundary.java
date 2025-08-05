package use_case.image.rotate;

public interface RotateOutputBoundary {
    void present(RotateResponseModel responseModel);
    void presentError(String error);
}
