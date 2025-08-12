package entity;

import java.util.*;

/**
 * Stores all past actions in stacks of done and undone actions.
 */
public class ActionHistory {
    private final Stack<Drawable> undoStack;
    private final Stack<Drawable> redoStack;
    private Drawable currentState;

    public ActionHistory() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    /**
     * Adds a new action (in the form of a drawable) to the undo stack.
     * @param newState the new drawable to push to the undo stack
     */
    public void push(Drawable newState) {
        if (currentState != null) {
            undoStack.push(currentState);
        }
        currentState = newState;
        redoStack.clear();
    }

    /**
     * Undoes the previous action.
     * @return Drawable the undone action
     */
    public Drawable undo() {
        if (currentState == null) {
            return null;
        }
        if (undoStack.isEmpty()) {
            redoStack.push(currentState);
            return null;
        }
        redoStack.push(currentState);
        currentState = undoStack.pop();
        return currentState;
    }

    /**
     * Redoes the previously undone action.
     * @return Drawable the redone action
     */
    public Drawable redo() {
        if (redoStack.isEmpty()) {
            return null;
        }
        if (currentState != null) {
            undoStack.push(currentState);
        }
        currentState = redoStack.pop();
        return currentState;
    }

    /**
     * Returns the current action.
     * @return Drawable the current action
     */
    public Drawable getCurrentState() {
        return currentState;
    }

    /**
     * Returns the undo stack.
     * @return Stack(Drawable) the undo stack.
     */
    public Stack<Drawable> getUndoStack() {
        return undoStack;
    }
}
