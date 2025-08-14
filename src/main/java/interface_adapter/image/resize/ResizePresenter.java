package interface_adapter.image.resize;

import javax.swing.JOptionPane;

import interface_adapter.canvas.DrawingViewModel;
import use_case.image.resize.ResizeOutputBoundary;
import use_case.image.resize.ResizeResponseModel;

public class ResizePresenter implements ResizeOutputBoundary {
    private final DrawingViewModel drawingViewModel;

    public ResizePresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void present(ResizeResponseModel responseModel) {
        drawingViewModel.setImageList(responseModel.getImages());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Resize Error", JOptionPane.ERROR_MESSAGE);
    }
}
