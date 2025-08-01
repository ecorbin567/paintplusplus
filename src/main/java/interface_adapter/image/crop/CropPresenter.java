package interface_adapter.image.crop;

import entity.DrawingCanvas;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropResponseModel;

import javax.swing.*;

/**
 * The presenter for the Crop Use Case.
 */
public class CropPresenter implements CropOutputBoundary {

    private final DrawingCanvas canvas;

    public CropPresenter(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(CropResponseModel responseModel) {
        canvas.repaint();  // Image is already updated
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error,  "Crop Error", JOptionPane.ERROR_MESSAGE);
    }
}
