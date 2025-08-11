package use_case.image.crop;

/**
 * Input Boundary for actions which are related to cropping an image.
 */
public interface CropInputBoundary {
    void execute(CropRequestModel requestModel);
}

