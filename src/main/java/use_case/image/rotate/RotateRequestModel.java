package use_case.image.rotate;

/**
 * Request model for the Rotate Image use case.
 *
 * @param degrees angle to rotate in degrees.
 */
public record RotateRequestModel(double degrees) {
}
