package app;

import entity.CanvasState;
import interface_adapter.newselection.SelectionPresenter;
import interface_adapter.newselection.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.DrawingViewModel;
import use_case.mouseui.MouseUILoggingDecorator;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIUseInteractor;
import use_case.newselection.NewSelectionInputBoundary;
import use_case.newselection.NewSelectionInteractor;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInteractor;

public class CanvasControllerFactory {
    private CanvasControllerFactory(){
        //Unneeded
    }
    /**
     * Factory function for creating the Canvas Controller.
     * @param canvasState the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @param selectionViewModel the SelectionViewModel
     * @return the SaveController created
     */
    public static CanvasController createCanvasController(CanvasState canvasState,
                                                          DrawingViewModel drawingViewModel,
                                                          SelectionViewModel selectionViewModel){
        // presenters
        final CanvasPresenter canvasPresenter = new CanvasPresenter(drawingViewModel);
        final SelectionPresenter selectionPresenter = new SelectionPresenter(selectionViewModel);
        // interactors
        final MouseUIUseInputBoundary mouseInteractor = new MouseUILoggingDecorator(new MouseUIUseInteractor(
                canvasState, canvasPresenter));

        final ToolUseInputBoundary toolUseInteractor = new ToolUseInteractor(canvasState);
        final NewSelectionInputBoundary selectionInteractor = new NewSelectionInteractor(canvasState,
                selectionPresenter);

        return new CanvasController(mouseInteractor, toolUseInteractor, selectionInteractor);
    }
}

