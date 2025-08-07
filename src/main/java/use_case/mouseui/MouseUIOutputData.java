package use_case.mouseui;

import entity.Drawable;

import java.util.Stack;

public class MouseUIOutputData {
    private final Stack<Drawable> drawables;
    private final boolean state;
    private final Drawable drawable;

    public MouseUIOutputData(Stack<Drawable> drawables, boolean state, Drawable drawable) {
        this.drawables = drawables;
        this.state = state;
        this.drawable = drawable;
    }

    public Stack<Drawable> getDrawables() {
        return drawables;
    }

    public boolean getState() {
        return state;
    }

    public Drawable getDrawable() {
        return drawable;
    }
}
