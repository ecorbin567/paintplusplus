package interface_adapter.newcanvas;

import interface_adapter.ViewModel;
import use_case.newcanvas.NewCanvasOutputData;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The View Model for the My Canvases View.
 */
public class NewCanvasViewModel extends ViewModel<NewCanvasState> {

    public String CANVAS_VIEW_TITLE = "My canvases";

    public NewCanvasViewModel() {
        super("my canvases");
        setState(new NewCanvasState());

    }

    private void updateNewCanvasTitle() {
        CANVAS_VIEW_TITLE = getState().getUsername() + "'s canvases";
    }

    public void setCanvases(List<BufferedImage> canvases) {
        // now logged in, update canvas title
        updateNewCanvasTitle();

        // yea
        List<BufferedImage> old = this.getState().getCanvases();
        this.getState().setCanvases(canvases);
        firePropertyChanged("canvases");
    }

    public List<BufferedImage> getCanvases() {
        return this.getState().getCanvases();
    }

}
