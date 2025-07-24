package use_case.image.import_image;

public interface ImportOutputBoundary {
    void present(ImportResponseModel response);
    void presentError(String message);
}
