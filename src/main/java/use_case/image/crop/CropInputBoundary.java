package use_case.image.crop;

/**
 * Input Boundary for actions which are related to cropping an image.
 */
public interface CropInputBoundary {
    /**
     * Prepares the crop operation.
     * @param requestModel the crop request model input data.
     */
    void execute(CropRequestModel requestModel);
}

