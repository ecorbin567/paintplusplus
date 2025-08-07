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

    public TopMenuOutputData(Stack<Drawable> drawableStack, boolean stackEmpty,
                             List<Image> importedImages) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
        this.scale = null;
        this.importedImages = importedImages;
    }

    public TopMenuOutputData(Stack<Drawable> drawableStack, boolean stackEmpty,
                             double scale) {
        this.drawableStack = drawableStack;
        this.stackEmpty = stackEmpty;
        this.scale = scale;
        this.importedImages = null;
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

    public List<Image> getImportedImages() {
        return importedImages;
    }

}
