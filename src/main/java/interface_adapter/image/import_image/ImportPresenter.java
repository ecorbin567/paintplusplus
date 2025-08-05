package interface_adapter.image.import_image;

import use_case.image.import_image.ImportOutputBoundary;
import use_case.image.import_image.ImportResponseModel;

import javax.swing.*;

/**
 * The presenter for the Import Use Case.
 */
public class ImportPresenter implements ImportOutputBoundary {

    private final entity.DrawingCanvas canvas;

    public ImportPresenter(entity.DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(ImportResponseModel response) {
        canvas.addDrawableElement(response.getImage());
    }

    @Override
    public void presentError(String message) {
        JOptionPane.showMessageDialog(null, message, "Import Error", JOptionPane.ERROR_MESSAGE);
    }
}
