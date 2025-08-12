package interface_adapter.midmenu.image.import_image;

import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.canvas.DrawingViewModel;
import use_case.image.import_image.ImportOutputBoundary;
import use_case.image.import_image.ImportResponseModel;

import javax.swing.*;

/**
 * The presenter for the Import Use Case.
 */
public class ImportPresenter implements ImportOutputBoundary {

    private final DrawingViewModel drawingViewModel;

    public ImportPresenter(DrawingViewModel drawingViewModel) {
        this.drawingViewModel = drawingViewModel;
    }

    @Override
    public void present(ImportResponseModel response) {
        drawingViewModel.setImageList(response.getImage());
    }

    @Override
    public void presentError(String message) {
        JOptionPane.showMessageDialog(null, message, "Import Error", JOptionPane.ERROR_MESSAGE);
    }
}
