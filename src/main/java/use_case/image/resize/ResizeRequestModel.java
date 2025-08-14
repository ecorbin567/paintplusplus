package use_case.image.resize;

/**
 * Request model for the Resize Image use case.
 *
 * @param newWidth  target width in pixels (>0)
 * @param newHeight target height in pixels (>0)
 */
public record ResizeRequestModel(int newWidth, int newHeight) {
}
