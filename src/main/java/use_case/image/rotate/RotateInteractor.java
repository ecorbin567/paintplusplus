package use_case.image.rotate;

import java.util.List;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import use_case.image.crop.CropInteractor;

/**
 * The Rotate Image interactor.
 */
public class RotateInteractor implements RotateInputBoundary {
    private final CanvasState canvasState;
    private final RotateOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public RotateInteractor(CanvasState canvasState, RotateOutputBoundary presenter) {
        this.canvasState = canvasState;
        this.presenter = presenter;
        this.actionHistory = canvasState.getActionHistory();
    }

    @Override
    public void execute(RotateRequestModel requestModel) {
        try {
            final Image originalImage = canvasState.getCurrentImage();
            if (originalImage == null) {
                presenter.presentError("No image to rotate.");
                return;
            }

            // 1. Create the next state by cloning the original
            final Image newImage = originalImage.clone();

            // 2. Modify the new clone with the rotation changes
            newImage.rotate(requestModel.degrees());

            // 3. Push the new, modified state to the history
            CropInteractor.updateCurrentImage(originalImage, newImage, actionHistory, canvasState);

            final List<Image> importedImages = canvasState.getImportedImages();
            // 4. Pass the new state to the presenter to update the view
            presenter.present(new RotateResponseModel(importedImages));

        }
        catch (Exception e) {
            presenter.presentError("Rotate failed: " + e.getMessage());
        }
    }
}
