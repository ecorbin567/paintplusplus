package interface_adapter.image.crop;

import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.canvas.DrawingViewModel;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropResponseModel;

import javax.swing.*;

public class CropPresenter implements CropOutputBoundary {

    private final DrawingViewModel drawingViewModel;

    public CropPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void present(CropResponseModel responseModel) {
        drawingViewModel.setImageList(responseModel.getImage());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error,  "Crop Error", JOptionPane.ERROR_MESSAGE);
    }
}
