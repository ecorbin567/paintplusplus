package use_case.image.resize;

import java.util.List;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import use_case.image.crop.CropInteractor;

/**
 * The Resize Interactor.
 */
public class ResizeInteractor implements ResizeInputBoundary {
    private final CanvasState canvas;
    private final ResizeOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public ResizeInteractor(CanvasState canvas, ResizeOutputBoundary presenter) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = this.canvas.getActionHistory();
    }

    @Override
    public void execute(ResizeRequestModel requestModel) {
        try {
            Image originalImage = canvas.getCurrentImage();
            if (originalImage == null) {
                presenter.presentError("No image to resize.");
                return;
            }

            // 1. Create the next state by cloning the original
            Image newImage = originalImage.clone();

            // 2. Modify the new clone with the resize changes
            newImage.resize(requestModel.newWidth(), requestModel.newHeight());

            // 3. Push the new, modified state to the history
            CropInteractor.updateCurrentImage(originalImage, newImage, actionHistory, canvas);
            List<Image> importedImages = canvas.getImportedImages();
            // 4. Pass the new state to the presenter to update the view
            presenter.present(new ResizeResponseModel(importedImages));

        }
        catch (Exception e) {
            presenter.presentError("Resize failed: " + e.getMessage());
        }
    }
}
