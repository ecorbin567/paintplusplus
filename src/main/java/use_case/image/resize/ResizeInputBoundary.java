package use_case.image.resize;

/**
 * The Input Boundary for the Resize Use case.
 */
public interface ResizeInputBoundary {

    /**
     * Executes the resize image use case.
     *
     * @param requestModel the resize request.
     */
    void execute(ResizeRequestModel requestModel);
}
