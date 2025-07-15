package entity;

import java.util.Stack;

public class ActionHistory {
    private Stack<CanvasState> history;

    public ActionHistory() {
        this.history = new Stack<>();
    }

    // TODO: implement
    public void push(CanvasState state) {

    }

    public CanvasState undo(CanvasState state) {
        return null;
    }

    public CanvasState redo(CanvasState state) {
        return null;
    }
}
