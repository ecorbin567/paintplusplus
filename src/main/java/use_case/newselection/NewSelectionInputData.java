package use_case.newselection;


/**
 * Immutable request sent from the controller to the interactor
 * every time the user does something with the selection tool.
 *
 *  action       – START, DRAG, COMMIT, or CANCEL
 *  x, y         – mouse coordinate (canvas space, before scaling)
 */
public record NewSelectionInputData(
        SelectionAction action,
        int x, int y,
        int w, int h) {

    public enum SelectionAction {
        START, DRAG, COMMIT, CANCEL
    }






}
