package use_case.topmenu.history;

import entity.*;
import entity.Image;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class HistoryInteractor implements HistoryInputBoundary{
    HistoryOutputBoundary presenter;
    CanvasState canvasState;
    ActionHistory actionHistory;

    public HistoryInteractor(CanvasState canvasState, HistoryOutputBoundary presenter) {
        this.canvasState = canvasState;
        this.presenter = presenter;
        this.actionHistory = canvasState.getActionHistory();
    }

    @Override
    public void undoDrawable() {
        Drawable prevState = actionHistory.undo();
        List<Image> importedImages = canvasState.getImportedImages();
        importedImages.clear();
        rebuildStateFromHistory();

        if (prevState instanceof Image image) {
            importedImages.add(image);
            this.canvasState.setCurrentImage(image);
        }
        boolean undoStackEmpty = !actionHistory.getUndoStack().isEmpty();
        Stack<Drawable> undoStack = actionHistory.getUndoStack();

        HistoryOutputData outputData = new HistoryOutputData(undoStack, undoStackEmpty, prevState);
        presenter.setRepaintState(outputData);
        presenter.setDrawables(outputData);
        presenter.setCurrentDrawable(outputData);

    }
    @Override
    public void redoDrawable() {
        Drawable nextState = actionHistory.redo();
        if (nextState == null) {
            return;
        }

        List<Image> importedImages = canvasState.getImportedImages();
        importedImages.clear();
        rebuildStateFromHistory();

        if (nextState instanceof entity.Image image) {
            importedImages.add(image);
            canvasState.setCurrentImage(image);
        }

        boolean undoStackEmpty = !actionHistory.getUndoStack().isEmpty();
        Stack<Drawable> undoStack = actionHistory.getUndoStack();

        HistoryOutputData outputData = new HistoryOutputData(undoStack, undoStackEmpty, nextState);
        presenter.setDrawables(outputData);
        presenter.setRepaintState(outputData);
        presenter.setCurrentDrawable(outputData);
    }

    @Override
    public void clearHistory() {
        actionHistory.clearHistory();
    }

    private void rebuildStateFromHistory(){
        canvasState.getCommitedSelections().clear();
        canvasState.getClearRegions().clear();
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
            this.canvasState.getClearRegions().add(new Rectangle(mr.from()));
            this.canvasState.getCommitedSelections().add(
                    new CanvasState.Pair<>(mr.image(), new Rectangle(mr.to())));
        }
    }
}
