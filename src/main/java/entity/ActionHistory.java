package entity;

import java.util.*;

/**
 * Stores all past actions in stacks of done and undone actions.
 */
public class ActionHistory {
    private final Deque<Drawable> undoStack;
    private final Deque<Drawable> redoStack;
    private Drawable currentState = null;

    public ActionHistory() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    public void push(Drawable newState) {
        if (currentState != null) {
            undoStack.push(currentState);
        }
        currentState = newState;
        redoStack.clear();
    }

    public Drawable undo() {
        if (currentState == null) return null; // nothing to do
        if (undoStack.isEmpty()){ // first action on canvas
            redoStack.push(currentState); // save it for redo
            currentState = null;
            return null;
        }
        // code below from before
        redoStack.push(currentState);
        currentState = undoStack.pop();
        return currentState;
    }

    public Drawable redo() {
        if (redoStack.isEmpty()) return null;
        if (currentState != null) { // might be null after the first redo
            undoStack.push(currentState);
        }
        currentState = redoStack.pop();
        return currentState;
    }

    public Drawable getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Drawable currentState) {
        this.currentState = currentState;
        if (!(undoStack.isEmpty())) {
            undoStack.pop();
        }
        push(currentState);
    }

    public Deque<Drawable> getUndoStack() {
        return undoStack;
    }

    /**
     * Clear the action history.
     */
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
}
