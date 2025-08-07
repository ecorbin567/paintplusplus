package interface_adapter.canvas;

import entity.Drawable;
import entity.Image;
import interface_adapter.ViewModel;

import java.util.List;
import java.util.Stack;

/**
 * The View Model for the Logged In View.
 */
public class CanvasViewModel extends ViewModel<CanvasUserState> {
    private boolean stackEmpty;
    private Stack<Drawable> drawables;
    private double scale;
    private List<Image> images;
    private Drawable currentDrawable;

    public CanvasViewModel() {
        super("canvas");
        setState(new CanvasUserState());
    }

    //Only Repaint if there are things in ActionHistoryUndoStack
    public void shouldRepaint(boolean stackEmpty) {this.stackEmpty = stackEmpty;}
    public boolean getRepaintState() {return this.stackEmpty;}

    //Retrieves ActionHistoryUndoStack
    public void setDrawables(Stack<Drawable> drawables) {this.drawables = drawables;}
    public Stack<Drawable> getDrawables() {return this.drawables;}

    //Retrieves Stack Head
    public void setDrawable(Drawable drawable) {this.currentDrawable = drawable;}
    public Drawable getDrawable() {return this.currentDrawable;}

    //Scale
    public void setScale(double scale) {this.scale = scale;}
    public double getScale() {return this.scale;}

    //Image
    public void setImageList(List<Image> images) {this.images = images;}
    public List<Image> getImageList() {return this.images;}
}
