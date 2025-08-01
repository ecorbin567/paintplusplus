package interface_adapter.canvas;

import entity.Drawable;
import entity.Image;
import interface_adapter.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * The View Model for the Logged In View.
 */
public class CanvasViewModel extends ViewModel<CanvasUserState> {
    private final List<Image> importedImages = new ArrayList<>();
    private final double scale = 1.0;

    public CanvasViewModel() {
        super("canvas");
        setState(new CanvasUserState());
    }

    public double getScale(){
        return this.scale;
    }

    public List<Image> getImportedImages(){
        return this.importedImages;
    }

    public List<Drawable> getDrawables(){
        
    }

}
