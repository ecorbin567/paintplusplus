package interface_adapter.image.crop;

import use_case.image.crop.CropInputBoundary;
import use_case.image.crop.CropRequestModel;

/**
 * The controller responsible for handling the crop image use case.
 * This class receives raw input data from the user interface, gathers it into
 * a request model, and passes it to the interactor to execute the logic.
 */
public class CropController {

    // The use case interactor that will perform the crop operation.
    private final CropInputBoundary interactor;

    /**
     * Constructs a new CropController with a dependency on the use case interactor.
     *
     * @param interactor The interactor that implements the CropInputBoundary
     *                   and will execute the crop logic.
     */
    public CropController(CropInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Packages the crop parameters into a request model and executes the use case.
     *
     * @param x      The x-coordinate of the top-left corner of the crop area.
     * @param y      the y-coordinate of the top-left corner of the crop area.
     * @param width  The width of the crop area.
     * @param height The height of the crop area.
     */
    public void execute(int x, int y, int width, int height) {
        final CropRequestModel request = new CropRequestModel(x, y, width, height);
        interactor.execute(request);
    }
}
