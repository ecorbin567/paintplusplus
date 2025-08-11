package use_case.topmenu.history;

import entity.Drawable;

import java.util.Stack;

public class HistoryOutputData {
    private final Drawable currentDrawable;
    private Stack<Drawable> drawableStack = null;
    private boolean stackEmpty;

    public HistoryOutputData(Stack<Drawable> drawableStack, boolean stackEmpty, Drawable currentDrawable) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
        this.currentDrawable = currentDrawable;
    }

    public Drawable getCurrentDrawable() {
        return currentDrawable;
    }

    public Stack<Drawable> getDrawableStack() {
        return drawableStack;
    }

    public boolean getStackEmpty() {
        return stackEmpty;
    }
}
