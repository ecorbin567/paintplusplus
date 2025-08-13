package use_case.image.crop;

import entity.ActionHistory;
import entity.CanvasState;
import entity.Image;
import java.util.List;

/**
 * The Crop Interactor.
 */
public class CropInteractor implements CropInputBoundary {

    private final CanvasState canvas;
    private final CropOutputBoundary presenter;
    private final ActionHistory actionHistory;

    public CropInteractor(CanvasState canvas, CropOutputBoundary presenter) {
        this.canvas = canvas;
        this.presenter = presenter;
        this.actionHistory = canvas.getActionHistory();
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
            newImage.crop(requestModel.x(), requestModel.y(), requestModel.width(), requestModel.height());
            updateCurrentImage(originalImage, newImage, actionHistory, canvas);
            List<Image> importedImages = canvas.getImportedImages();

            presenter.present(new CropResponseModel(importedImages));

        } catch (Exception e) {
            presenter.presentError("Crop failed: " + e.getMessage());
        }
    }

    public static void updateCurrentImage(Image originalImage, Image newImage, ActionHistory actionHistory, CanvasState canvas) {
        actionHistory.push(newImage);

        List<Image> importedImages = canvas.getImportedImages();
        int index = importedImages.indexOf(originalImage);
        if(index != -1){
            importedImages.set(index, newImage);
        }
        else{
            importedImages.add(newImage);
        }

        canvas.setCurrentImage(newImage);
    }
}
