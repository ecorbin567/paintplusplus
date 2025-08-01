package use_case.image.crop;

import entity.ActionHistory;
import entity.DrawingCanvas;
import entity.Image;

/**
 * The Crop Interactor.
 */
public class CropInteractor implements CropInputBoundary {

    private final DrawingCanvas canvas;
    private final CropOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public CropInteractor(DrawingCanvas canvas, CropOutputBoundary presenter, ActionHistory actionHistory) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = actionHistory;
    }

    @Override
    public void execute(CropRequestModel requestModel) {
        try {
            Image image = canvas.getCurrentImage();
            if (image == null) {
                presenter.presentError("No image to crop.");
                return;
            }

            actionHistory.push(image.clone());

            image.crop(
                    requestModel.getX(),
                    requestModel.getY(),
                    requestModel.getWidth(),
                    requestModel.getHeight()
            );

            presenter.present(new CropResponseModel(image));
            canvas.repaint();

        } catch (Exception e) {
            presenter.presentError("Crop failed: " + e.getMessage());
        }
    }
}
