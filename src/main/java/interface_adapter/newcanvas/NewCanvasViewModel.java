package interface_adapter.newcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

import interface_adapter.ViewModel;

/**
 * The View Model for the My Canvases View.
 */
public class NewCanvasViewModel extends ViewModel<NewCanvasState> {

    public static final String CANVAS_VIEW_TITLE = "My canvases";

    public NewCanvasViewModel() {
        super("my canvases");
        setState(new NewCanvasState());

    }

    /**
     * Set displayed canvases and fire property change.
     *
     * @param canvases the list of canvases
     */
    public void setCanvases(List<BufferedImage> canvases) {
        // yea
        this.getState().setCanvases(canvases);
        firePropertyChanged("canvases");
    }

    public List<BufferedImage> getCanvases() {
        return this.getState().getCanvases();
    }

}
