package interface_adapter.image.resize;

import interface_adapter.canvas.CanvasViewModel;
import use_case.image.resize.ResizeOutputBoundary;
import use_case.image.resize.ResizeResponseModel;
import javax.swing.*;

public class ResizePresenter implements ResizeOutputBoundary {
    private final CanvasViewModel canvasViewModel;

    public ResizePresenter(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void present(ResizeResponseModel responseModel) {
        canvasViewModel.setImageList(responseModel.getImages());
    }

    @Override
    public void presentError(String error) {
        JOptionPane.showMessageDialog(null, error, "Resize Error", JOptionPane.ERROR_MESSAGE);
    }
}
