package use_case.image.rotate;

/**
 * The import boundary for the Rotate use case.
 */

public interface RotateInputBoundary {

    /**
     * Executes the Rotate Image use case.
     * @param requestModel the Rotate request.
     */
    void execute(RotateRequestModel requestModel);
}
