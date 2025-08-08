package interface_adapter.image.crop;

import entity.DrawingCanvas;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropResponseModel;

import javax.swing.*;

/**
 * The presenter for the Crop Use Case.
 */
public class CropPresenter implements CropOutputBoundary {

    // The UI component that will be updated by this presenter.
    private final DrawingCanvas canvas;

    /**
     * Constructs a new CropPresenter.
     * @param canvas The DrawingCanvas that this presenter will update.
     */
    public CropPresenter(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Prepares and displays the result of the crop operation.
     * This method is called by the interactor on a successful crop. It takes the new
     * image state from the response model and instructs the canvas to update itself.
     * @param responseModel The data transfer object containing the successfully cropped image.
     */
    @Override
    public void present(CropResponseModel responseModel) {
        canvas.updateCurrentImage(responseModel.getImage());
    }

    /**
     * Prepares and displays an error message to the user.
     * This method is called by the interactor when the crop operation fails.
     * It displays a pop-up dialog with the provided error message.
     * @param error A string describing the error that occurred.
     */
    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error,  "Crop Error", JOptionPane.ERROR_MESSAGE);
    }
}
