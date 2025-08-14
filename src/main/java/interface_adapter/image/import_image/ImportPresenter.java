package interface_adapter.image.import_image;

import javax.swing.JOptionPane;

import interface_adapter.canvas.DrawingViewModel;
import use_case.image.import_image.ImportOutputBoundary;
import use_case.image.import_image.ImportResponseModel;

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
        drawingViewModel.setImageList(response.image());
    }

    @Override
    public void presentError(String message) {
        JOptionPane.showMessageDialog(null, message, "Import Error", JOptionPane.ERROR_MESSAGE);
    }
}
