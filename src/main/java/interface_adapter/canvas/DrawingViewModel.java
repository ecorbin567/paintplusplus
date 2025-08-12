package interface_adapter.canvas;

import entity.Drawable;
import entity.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DrawingViewModel {
    private boolean stackEmpty = true;
    private Stack<Drawable> drawables = new Stack<>();
    private double scale = 1.0;
    private List<Image> images = new ArrayList<>();
    private Drawable currentDrawable;
    private String currentUser;

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

    public void setCurrentUser(String username) {this.currentUser = username;}
    public String getCurrentUser() {return currentUser;}
}
