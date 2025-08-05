package interface_adapter.image.rotate;

import entity.DrawingCanvas;
import use_case.image.rotate.RotateOutputBoundary;
import use_case.image.rotate.RotateResponseModel;
import javax.swing.*;

public class RotatePresenter implements RotateOutputBoundary {
    private final DrawingCanvas canvas;

    public RotatePresenter(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(RotateResponseModel responseModel) {
        canvas.repaint();
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Rotate Error", JOptionPane.ERROR_MESSAGE);
    }
}
