package use_case.topmenu;

import entity.Drawable;
import entity.Image;

import java.util.List;
import java.util.Stack;

public class TopMenuOutputData {

    private Stack<Drawable> drawableStack = null;
    private boolean stackEmpty;
    private Double scale;
    private List<Image> importedImages;
    private Drawable currentDrawable;

    public TopMenuOutputData(Stack<Drawable> drawableStack, boolean stackEmpty,
                             List<Image> importedImages, Drawable currentDrawable) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
        this.scale = null;
        this.importedImages = importedImages;
        this.currentDrawable = currentDrawable;
    }

    public TopMenuOutputData(Stack<Drawable> drawableStack, boolean stackEmpty,
                             double scale, Drawable currentDrawable) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
        this.scale = scale;
        this.importedImages = null;
        this.currentDrawable = currentDrawable;
    }

    public Stack<Drawable> getDrawables() {
        return drawableStack;
    }

    public boolean isStackEmpty() {
        return stackEmpty;
    }

    public Double getScale() {
        return scale;
    }

    public Drawable getCurrentDrawable() {
        return currentDrawable;
    }
}
