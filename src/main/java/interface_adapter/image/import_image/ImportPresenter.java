package interface_adapter.image.import_image;

import entity.Image;
import use_case.image.import_image.ImportOutputBoundary;
import use_case.image.import_image.ImportResponseModel;

import javax.swing.*;

public class ImportPresenter implements ImportOutputBoundary {

    private final entity.DrawingCanvas canvas;

    public ImportPresenter(entity.DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(ImportResponseModel response) {
        Image importedImage = response.getImage();
        canvas.addDrawableElement(importedImage);
        canvas.repaint();
    }

    @Override
    public void presentError(String message) {
        JOptionPane.showMessageDialog(null, message, "Import Error", JOptionPane.ERROR_MESSAGE);
    }
}
