package use_case.image.crop;

import entity.DrawingCanvas;
import entity.Image;

public class CropInteractor implements CropInputBoundary {

    private final DrawingCanvas canvas;
    private final CropOutputBoundary presenter;

    public CropInteractor(DrawingCanvas canvas, CropOutputBoundary presenter) {
        this.canvas = canvas;
        this.presenter = presenter;
    }

    @Override
    public void execute(CropRequestModel requestModel) {
        try {
            Image image = canvas.getCurrentImage();
            if (image == null) {
                presenter.presentError("No image to crop.");
                return;
            }

            image.crop(requestModel.getX(), requestModel.getY(), requestModel.getWidth(), requestModel.getHeight());
            presenter.present(new CropResponseModel(image));
            canvas.repaint();
        } catch (Exception e) {
            presenter.presentError("Crop failed: " + e.getMessage());
        }
    }
}
