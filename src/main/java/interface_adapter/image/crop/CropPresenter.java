package interface_adapter.image.crop;

import interface_adapter.canvas.CanvasViewModel;
import use_case.image.crop.CropOutputBoundary;
import use_case.image.crop.CropResponseModel;

import javax.swing.*;

public class CropPresenter implements CropOutputBoundary {

    private final CanvasViewModel canvasViewModel;

    public CropPresenter(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void present(CropResponseModel responseModel) {
        canvasViewModel.setImageList(responseModel.getImage());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error,  "Crop Error", JOptionPane.ERROR_MESSAGE);
    }
}
