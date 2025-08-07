package interface_adapter.image.rotate;

import interface_adapter.canvas.CanvasViewModel;
import use_case.image.rotate.RotateOutputBoundary;
import use_case.image.rotate.RotateResponseModel;
import javax.swing.*;

public class RotatePresenter implements RotateOutputBoundary {
    private final CanvasViewModel canvasViewModel;

    public RotatePresenter(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void present(RotateResponseModel responseModel) {
        canvasViewModel.setImageList(responseModel.getImage());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Rotate Error", JOptionPane.ERROR_MESSAGE);
    }
}
