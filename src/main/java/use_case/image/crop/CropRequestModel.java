package use_case.image.crop;

/**
 * Obtains information from the user about the x and y positions, and the width and height,
 * of the cropped image.
 */
public record CropRequestModel(int x, int y, int width, int height) {

}
