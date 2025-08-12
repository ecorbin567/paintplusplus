package use_case.topmenu.save;

import data_access.CanvasDataAccessInterface;
import entity.CanvasState;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveInteractor implements SaveInputBoundary{
    private final CanvasState canvasState;
    private final SaveFileGateway fileSaveGateway;
    private static final Logger logger = Logger.getLogger(SaveInteractor.class.getName());
    private final CanvasDataAccessInterface  canvasDataAccessObject;

    public SaveInteractor(CanvasState canvasState,
                          SaveFileGateway fileSaveGateway, CanvasDataAccessInterface canvasDataAccessObject) {
        this.canvasState = canvasState;
        this.fileSaveGateway = fileSaveGateway;
        this.canvasDataAccessObject = canvasDataAccessObject;
    }

    @Override
    public void save(SaveInputData inputData) {
        File file = inputData.file();
        BufferedImage image = inputData.image();
        try{
            this.fileSaveGateway.saveImage(image, file);
            setBufferedImage(inputData);
            setFile(inputData);
            canvasState.setFilePath(file);
        }

        catch(IOException e){
            logger.log(Level.SEVERE, "Error saving image", e);
        }
    }

    @Override
    public void saveCanvasOnline(String username, BufferedImage image) {
        canvasDataAccessObject.saveCanvas(username, image);
    }

    private void setBufferedImage(SaveInputData inputData) {
        BufferedImage image = inputData.image();
        this.canvasState.setSavedImage(image);
    }

    private void setFile(SaveInputData inputData) {
        File file = inputData.file();
        this.canvasState.setFilePath(file);
    }
}
