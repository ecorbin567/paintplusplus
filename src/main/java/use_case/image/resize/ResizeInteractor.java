package use_case.image.resize;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.Image;

public class ResizeInteractor implements ResizeInputBoundary {
    private final DrawingCanvas canvas;
    private final ResizeOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public ResizeInteractor(DrawingCanvas canvas, ResizeOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(ResizeRequestModel requestModel) {
        try {
            Image image = canvas.getCurrentImage();
            if (image == null) {
                presenter.presentError("No image to resize.");
                return;
            }

            actionHistory.push(image.clone());
            image.resize(requestModel.getNewWidth(), requestModel.getNewHeight());

            presenter.present(new ResizeResponseModel(image));
            canvas.repaint();
        } catch (Exception e) {
            presenter.presentError("Resize failed: " + e.getMessage());
        }
    }
}
