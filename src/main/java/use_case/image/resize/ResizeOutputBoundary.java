package use_case.image.resize;

public interface ResizeOutputBoundary {
    void present(ResizeResponseModel responseModel);
    void presentError(String error);
}
