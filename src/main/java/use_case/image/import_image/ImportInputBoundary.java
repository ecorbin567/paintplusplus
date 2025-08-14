package use_case.image.import_image;

/**
 * Input Boundary for actions which are related to importing an image.
 */
public interface ImportInputBoundary {

    /**
     * Executes the import image use case.
     *
     * @param request the import request.
     */
    void execute(ImportRequestModel request);
}
