package use_case.topmenu;

import entity.*;
import entity.Image;
import use_case.tooluse.ToolUseInputData;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
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
            canvasState.setSavedImage(image);
            canvasState.setFilePath(file);
        }
        catch(ImageSaveException e){
            logger.log(Level.SEVERE, "Error saving image", e);
        }
    }

    @Override
    public void undoDrawable(TopMenuInputData inputData) {
        ToolEnum toolName = inputData.getToolName();
        if (toolName == ToolEnum.UNDO) {
            ActionHistory actionHistory = canvasState.getActionHistory();
            Drawable prevState = actionHistory.undo();
            List<Image> importedImages = canvasState.getImportedImages();
            importedImages.clear();
            rebuildStateFromHistory();

            if (prevState instanceof Image image) {
                importedImages.add(image);
                this.canvasState.setCurrentImage(image);
            }

            boolean undoStackEmpty = actionHistory.getUndoStack().isEmpty();
            Stack<Drawable> undoStack = actionHistory.getUndoStack();

            TopMenuOutputData outputData = new TopMenuOutputData(undoStack, undoStackEmpty, importedImages);
            outputBoundary.setRepaintState(outputData);
            outputBoundary.setDrawables(outputData);
        }
    }

    @Override
    public void redoDrawable(TopMenuInputData inputData) {
        ToolEnum toolEnum = inputData.getToolName();
        if (toolEnum == ToolEnum.REDO) {
            ActionHistory actionHistory = this.canvasState.getActionHistory();
            Drawable nextState = actionHistory.redo();
            List<Image> importedImages = canvasState.getImportedImages();
            importedImages.clear();
            rebuildStateFromHistory();

            if (nextState instanceof entity.Image image) {
                importedImages.add(image);
                this.canvasState.setCurrentImage(image);
            }

            boolean undoStackEmpty = actionHistory.getUndoStack().isEmpty();
            Stack<Drawable> undoStack = actionHistory.getUndoStack();

            TopMenuOutputData outputData = new TopMenuOutputData(undoStack, undoStackEmpty, importedImages);
            outputBoundary.setDrawables(outputData);
            outputBoundary.setRepaintState(outputData);
        }
    }

    private void rebuildStateFromHistory(){
        this.canvasState.getCommitedSelections().clear();
        this.canvasState.getClearRegions().clear();
        ActionHistory actionHistory = this.canvasState.getActionHistory();

        for (Drawable d: actionHistory.getUndoStack()){
            addFromDrawable(d);
        }

        addFromDrawable(actionHistory.getCurrentState());
    }

    // helper function for rebuildStateFromHistory()
    private void addFromDrawable(Drawable d) {
        if (d instanceof PasteRecord pr) {
            this.canvasState.getCommitedSelections().add(
                    new CanvasState.Pair<>(pr.image, new Rectangle(pr.bounds)));
        } else if (d instanceof CutRecord cr) {
            this.canvasState.getClearRegions().add(new Rectangle(cr.bounds));

        } else if (d instanceof  MoveRecord mr){
            this.canvasState.getClearRegions().add(new Rectangle(mr.from));
            this.canvasState.getCommitedSelections().add(
                    new CanvasState.Pair<>(mr.image, new Rectangle(mr.to)));
        }
    }

    @Override
    public void zoomIn(TopMenuInputData inputData) {
        ToolEnum toolName = inputData.getToolName();
        if (toolName == ToolEnum.ZOOMIN) {
            double scale = canvasState.getScale() + 0.1;
            setScaleOutput(scale);
        }
    }

    private void setScaleOutput(double scale) {
        canvasState.setScale(scale);
        ActionHistory actionHistory = this.canvasState.getActionHistory();
        Stack<Drawable> undoStack = actionHistory.getUndoStack();
        boolean undoStackEmpty = undoStack.isEmpty();
        TopMenuOutputData outputData = new TopMenuOutputData(undoStack, undoStackEmpty, scale);
        outputBoundary.setDrawables(outputData);
        outputBoundary.setRepaintState(outputData);
        outputBoundary.setScale(outputData);
    }

    @Override
    public void zoomOut(TopMenuInputData inputData) {
        ToolEnum toolName = inputData.getToolName();
        if(toolName == ToolEnum.ZOOMOUT){
            double scale = canvasState.getScale() - 0.1;
            setScaleOutput(scale);
        }
    }

}
