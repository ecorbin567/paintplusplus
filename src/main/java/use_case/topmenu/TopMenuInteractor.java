package use_case.topmenu;

import entity.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopMenuInteractor implements TopMenuInputBoundary {
    private final CanvasState canvasState;
    private final TopMenuOutputBoundary outputBoundary;
    private final FileSaveGateway fileSaveGateway;
    private static final Logger logger = Logger.getLogger(TopMenuInteractor.class.getName());



    public TopMenuInteractor(CanvasState canvasState,
                             TopMenuOutputBoundary outputBoundary,
                             FileSaveGateway fileSaveGateway) {
        this.canvasState = canvasState;
        this.outputBoundary = outputBoundary;
        this.fileSaveGateway = fileSaveGateway;
    }

    @Override
    public void setTool(TopMenuInputData inputData) {
        ToolEnum toolName = inputData.getToolName();
        this.canvasState.setButtonPressed(toolName);
    }

    @Override
    public void setBufferedImage(TopMenuInputData inputData) {
        BufferedImage image = inputData.getImage();
        this.canvasState.setSavedImage(image);
    }

    @Override
    public void setFile(TopMenuInputData inputData) {
        File file = inputData.getFile();
        this.canvasState.setFilePath(file);
    }

    public void save(TopMenuInputData inputData) {
        File file = inputData.getFile();
        BufferedImage image = inputData.getImage();
        try{
            this.fileSaveGateway.saveImage(image, file);
        }
        catch(ImageSaveException e){
            logger.log(Level.SEVERE, "Error saving image", e);
        }
    }

    public void undoDrawable() {
        ActionHistory actionHistory = canvasState.getActionHistory();
        Drawable prevState = actionHistory.undo();

        if (prevState instanceof entity.Image image) {
            this.canvasState.setCurrentImage(image);
        }

        boolean undoStackEmpty = actionHistory.getUndoStack().isEmpty();
        Stack<Drawable> undoStack = actionHistory.getUndoStack();

        TopMenuOutputData outputData = new TopMenuOutputData(undoStack, undoStackEmpty);
        outputBoundary.setRepaintState(outputData);
        outputBoundary.setDrawables(outputData);
    }

    public void redoDrawable() {
        ActionHistory actionHistory = this.canvasState.getActionHistory();
        Drawable nextState = actionHistory.redo();

        if (nextState instanceof entity.Image image) {
            this.canvasState.setCurrentImage(image);
        }

        boolean undoStackEmpty = actionHistory.getUndoStack().isEmpty();
        Stack<Drawable> undoStack = actionHistory.getUndoStack();

        TopMenuOutputData outputData = new TopMenuOutputData(undoStack, undoStackEmpty);
        outputBoundary.setDrawables(outputData);
        outputBoundary.setRepaintState(outputData);
    }

}
