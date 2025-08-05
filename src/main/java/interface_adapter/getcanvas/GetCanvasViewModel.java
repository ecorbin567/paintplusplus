package interface_adapter.getcanvas;

import interface_adapter.ViewModel;

/**
 * The View Model for the My Canvases View.
 */
public class GetCanvasViewModel extends ViewModel<GetCanvasState> {

    public GetCanvasViewModel() {
        super("my canvases");
        setState(new GetCanvasState());
    }

}
