package use_case.image.rotate;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.Image;

public class RotateInteractor implements RotateInputBoundary {
    private final DrawingCanvas canvas;
    private final RotateOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public RotateInteractor(DrawingCanvas canvas, RotateOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(RotateRequestModel requestModel) {
        try {
            Image image = canvas.getCurrentImage();
            if (image == null) {
                presenter.presentError("No image to rotate.");
                return;
            }

            actionHistory.push(image.clone());
            image.rotate(requestModel.getDegrees());

            presenter.present(new RotateResponseModel(image));
            canvas.repaint();
        } catch (Exception e) {
            presenter.presentError("Rotate failed: " + e.getMessage());
        }
    }
}
