package use_case.topmenu.save;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.CanvasState;

public class SaveInteractor implements SaveInputBoundary {
    private final CanvasState canvasState;
    private final SaveFileGateway fileSaveGateway;
    private static final Logger logger = Logger.getLogger(SaveInteractor.class.getName());

    public SaveInteractor(CanvasState canvasState,
                          SaveFileGateway fileSaveGateway) {
        this.canvasState = canvasState;
        this.fileSaveGateway = fileSaveGateway;
    }

    @Override
    public void save(SaveInputData inputData) {
        final File file = inputData.file();
        final BufferedImage image = inputData.image();
        try {
            this.fileSaveGateway.saveImage(image, file);
            setBufferedImage(inputData);
            setFile(inputData);
            canvasState.setFilePath(file);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving image", e);
        }
    }

    private void setBufferedImage(SaveInputData inputData) {
        final BufferedImage image = inputData.image();
        this.canvasState.setSavedImage(image);
    }

    private void setFile(SaveInputData inputData) {
        final File file = inputData.file();
        this.canvasState.setFilePath(file);
    }
}
