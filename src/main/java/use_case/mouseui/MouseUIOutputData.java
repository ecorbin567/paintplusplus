package use_case.mouseui;

import entity.Drawable;

import java.util.Stack;

public class MouseUIOutputData {
    private final Stack<Drawable> drawables;
    private final boolean state;

    public MouseUIOutputData(Stack<Drawable> drawables, boolean state){
        this.drawables = drawables;
        this.state = state;
    }

    public Stack<Drawable> getDrawables() {
        return drawables;
    }

    public boolean getState() {
        return state;
    }
}
