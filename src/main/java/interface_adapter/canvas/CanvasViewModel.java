package interface_adapter.canvas;

import interface_adapter.ViewModel;

/**
 * The View Model for the Logged In View.
 */
public class CanvasViewModel extends ViewModel<CanvasState> {

    public CanvasViewModel() {
        super("canvas");
        setState(new CanvasState());
    }

}
