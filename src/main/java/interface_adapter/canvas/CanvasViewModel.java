package interface_adapter.canvas;

import entity.Drawable;
import interface_adapter.ViewModel;
import java.util.Stack;

/**
 * The View Model for the Logged In View.
 */
public class CanvasViewModel extends ViewModel<CanvasUserState> {
    private final double scale = 1.0;
    private boolean stackEmpty;
    private Stack<Drawable> drawables;

    public CanvasViewModel() {
        super("canvas");
        setState(new CanvasUserState());
    }

    public double getScale(){
        return this.scale;
    }

    //Only Repaint if there are things in ActionHistoryUndoStack
    public void shouldRepaint(boolean stackEmpty) {this.stackEmpty = stackEmpty;}
    public boolean getRepaintState() {return this.stackEmpty;}

    //Retrieves ActionHistoryUndoStack
    public void setDrawables(Stack<Drawable> drawables) {this.drawables = drawables;}
    public Stack<Drawable> getDrawables() {return this.drawables;}
}
