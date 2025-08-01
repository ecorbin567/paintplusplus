package use_case.image.import_image;

/**
 * Input Boundary for actions which are related to importing an image.
 */
public interface ImportInputBoundary {
    void execute(ImportRequestModel request);
}
