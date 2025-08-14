package interface_adapter.image.rotate;

import javax.swing.JOptionPane;

import interface_adapter.canvas.DrawingViewModel;
import use_case.image.rotate.RotateOutputBoundary;
import use_case.image.rotate.RotateResponseModel;

public class RotatePresenter implements RotateOutputBoundary {
    private final DrawingViewModel drawingViewModel;

    public RotatePresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void present(RotateResponseModel responseModel) {
        drawingViewModel.setImageList(responseModel.getImage());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Rotate Error", JOptionPane.ERROR_MESSAGE);
    }
}
