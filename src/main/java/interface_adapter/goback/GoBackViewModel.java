package interface_adapter.goback;

import interface_adapter.ViewModel;

/**
 * The View Model for the My Canvases View.
 */
public class GoBackViewModel extends ViewModel<GoBackState> {

    public GoBackViewModel() {
        super("my canvases");
        setState(new GoBackState());
    }

}
