package entity;

import java.util.*;

/**
 * Stores all past actions in stacks of done and undone actions.
 */
public class ActionHistory {
    private final Stack<Editable> undoStack;
    private final Stack<Editable> redoStack;
    private Editable currentState = null;

    public ActionHistory() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void push(Editable newState) {
        if (currentState != null) {
            undoStack.push(currentState);
        }
        currentState = newState;
        redoStack.clear();
    }

    public Editable undo() {
        if (undoStack.isEmpty()) return null;
        redoStack.push(currentState);
        currentState = undoStack.pop();
        return currentState;
    }

    public Editable redo() {
        if (redoStack.isEmpty()) return null;
        undoStack.push(currentState);
        currentState = redoStack.pop();
        return currentState;
    }

    public Editable getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Editable currentState) {
        this.currentState = currentState;
        if (!(undoStack.isEmpty())) {
            undoStack.pop();
        }
        push(currentState);
    }

    public Stack<Editable> getUndoStack() {
        return undoStack;
    }
}
