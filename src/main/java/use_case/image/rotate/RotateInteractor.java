package use_case.image.rotate;

import entity.ActionHistory;
import entity.CanvasState;
import entity.DrawingCanvas;
import entity.Image;
import view.CanvasView;

public class RotateInteractor implements RotateInputBoundary {
    private CanvasState canvasState;
    private final RotateOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public RotateInteractor(CanvasState canvasState, RotateOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvasState = canvasState;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(RotateRequestModel requestModel) {
        try {
            Image originalImage = canvasState.getCurrentImage();
            if (originalImage == null) {
                presenter.presentError("No image to rotate.");
                return;
            }

            // 1. Create the next state by cloning the original
            Image newImage = originalImage.clone();

            // 2. Modify the new clone with the rotation changes
            newImage.rotate(requestModel.getDegrees());

            // 3. Push the new, modified state to the history
            actionHistory.push(newImage);

            // 4. Pass the new state to the presenter to update the view
            presenter.present(new RotateResponseModel(newImage));

        } catch (Exception e) {
            presenter.presentError("Rotate failed: " + e.getMessage());
        }
    }
}
