package use_case.topmenu;

import entity.Drawable;

import java.util.Stack;

public class TopMenuOutputData {

    private Stack<Drawable> drawableStack = null;
    private boolean stackEmpty;

    public TopMenuOutputData(Stack<Drawable> drawableStack, boolean stackEmpty) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
    }

    public TopMenuOutputData(String message) {
        this.message = message;
    }

    public Stack<Drawable> getDrawables() {
        return drawableStack;
    }

    public boolean isStackEmpty() {
        return stackEmpty;
    }

}
