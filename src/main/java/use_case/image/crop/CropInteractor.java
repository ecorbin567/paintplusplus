package use_case.image.crop;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.Image;
import interface_adapter.canvas.CanvasState;

public class CropInteractor implements CropInputBoundary {

    private final CanvasState canvas;
    private final CropOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public CropInteractor(CanvasState canvas, CropOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(CropRequestModel requestModel) {
        try {
            Image originalImage = canvas.getCurrentImage();
            if (originalImage == null) {
                presenter.presentError("No image to modify."); // Use a generic error
                return;
            }

            Image newImage = originalImage.clone();

            newImage.crop(requestModel.getX(), requestModel.getY(), requestModel.getWidth(), requestModel.getHeight());

            actionHistory.push(newImage);

            presenter.present(new CropResponseModel(newImage));

        } catch (Exception e) {
            presenter.presentError("Crop failed: " + e.getMessage());
        }
    }
}
