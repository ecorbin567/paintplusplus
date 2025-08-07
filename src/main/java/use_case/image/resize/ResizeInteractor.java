package use_case.image.resize;

import entity.ActionHistory;
import entity.CanvasState;
import entity.DrawingCanvas;
import entity.Image;

public class ResizeInteractor implements ResizeInputBoundary {
    private final CanvasState canvas;
    private final ResizeOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public ResizeInteractor(CanvasState canvas, ResizeOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
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
            newImage.resize(requestModel.getNewWidth(), requestModel.getNewHeight());

            // 3. Push the new, modified state to the history
            actionHistory.push(newImage);

            // 4. Pass the new state to the presenter to update the view
            presenter.present(new ResizeResponseModel(newImage));

        } catch (Exception e) {
            presenter.presentError("Resize failed: " + e.getMessage());
        }
    }
}
