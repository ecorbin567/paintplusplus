package interface_adapter.newCanvas;

import interface_adapter.ViewModel;

/**
 * The View Model for the My Canvases View.
 */
public class NewCanvasViewModel extends ViewModel<NewCanvasState> {

    public NewCanvasViewModel() {
        super("my canvases");
        setState(new NewCanvasState());
    }

}
