package entity;

import java.util.*;

public class ActionHistory {
    private final Stack<CanvasState> undoStack;
    private final Stack<CanvasState> redoStack;
    private CanvasState currentState = null;

    public ActionHistory(CanvasState initialState) {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.currentState = initialState;
    }

    public void push(CanvasState newState) {
        if (currentState != null) {
            undoStack.push(currentState);
        }
        currentState = newState;
        redoStack.clear();
    }


    public CanvasState undo() {
        if (undoStack.isEmpty()) return null;
        redoStack.push(currentState);
        currentState = undoStack.pop();
        return currentState;
    }


    public CanvasState redo() {
        if (redoStack.isEmpty()) return null;
        undoStack.push(currentState);
        currentState = redoStack.pop();
        return currentState;
    }

    public CanvasState getCurrentState() {
        return currentState;
    }
}
