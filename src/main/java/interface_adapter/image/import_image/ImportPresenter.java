package interface_adapter.image.import_image;

import interface_adapter.canvas.CanvasViewModel;
import use_case.image.import_image.ImportOutputBoundary;
import use_case.image.import_image.ImportResponseModel;

import javax.swing.*;

public class ImportPresenter implements ImportOutputBoundary {

    private final CanvasViewModel canvasViewModel;

    public ImportPresenter(CanvasViewModel canvasViewModel) {
        this.canvasViewModel = canvasViewModel;
    }

    @Override
    public void present(ImportResponseModel response) {
        canvasViewModel.setImageList(response.getImage());
    }

    @Override
    public void presentError(String message) {
        JOptionPane.showMessageDialog(null, message, "Import Error", JOptionPane.ERROR_MESSAGE);
    }
}
