package app;

import entity.CanvasState;
import interface_adapter.newselection.SelectionPresenter;
import interface_adapter.newselection.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.DrawingViewModel;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIUseInteractor;
import use_case.newselection.NewSelectionInputBoundary;
import use_case.newselection.NewSelectionInteractor;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInteractor;

public class CanvasControllerFactory {
    public static CanvasController createCanvasController(CanvasState canvasState,
                                                          DrawingViewModel drawingViewModel,
                                                          SelectionViewModel selectionViewModel){
        // presenters
        CanvasPresenter canvasPresenter = new CanvasPresenter(drawingViewModel);
        SelectionPresenter selectionPresenter = new SelectionPresenter(selectionViewModel);
        // interactors
        MouseUIUseInputBoundary mouseInteractor = new MouseUIUseInteractor(canvasState,
                canvasPresenter);
        ToolUseInputBoundary toolUseInteractor = new ToolUseInteractor(canvasState);
        NewSelectionInputBoundary selectionInteractor = new NewSelectionInteractor(canvasState,
                selectionPresenter);

        // TODO: controller constructor messed up with merge conflict
        return new CanvasController(mouseInteractor, toolUseInteractor, selectionInteractor);
    }
}

