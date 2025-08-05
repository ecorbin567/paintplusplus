package interface_adapter.image.resize;

import entity.DrawingCanvas;
import use_case.image.resize.ResizeOutputBoundary;
import use_case.image.resize.ResizeResponseModel;
import javax.swing.*;

public class ResizePresenter implements ResizeOutputBoundary {
    private final DrawingCanvas canvas;

    public ResizePresenter(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(ResizeResponseModel responseModel) {
        canvas.repaint();
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Resize Error", JOptionPane.ERROR_MESSAGE);
    }
}
