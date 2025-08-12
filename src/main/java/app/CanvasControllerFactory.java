package app;

import data_access.CanvasDataAccessInterface;
import entity.CanvasState;
import interface_adapter.SelectionPresenter;
import interface_adapter.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.DrawingViewModel;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIUseInteractor;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInteractor;

public class CanvasControllerFactory {
    /**
     * Factory function for creating the Canvas Controller.
     * @param canvasState the CanvasState
     * @param drawingViewModel the DrawingViewModel
     * @param selectionViewModel the SelectionViewModel
     * @return the SaveController created
     */
    public static CanvasController createCanvasController(CanvasState canvasState,
                                                          DrawingViewModel drawingViewModel,
                                                          SelectionViewModel selectionViewModel) {
        final CanvasPresenter canvasPresenter = new CanvasPresenter(drawingViewModel);
        final SelectionPresenter selectionPresenter = new SelectionPresenter(selectionViewModel);

        final MouseUIUseInputBoundary mouseInteractor = new MouseUIUseInteractor(canvasState,
                canvasPresenter, selectionPresenter);

        final ToolUseInputBoundary toolUseInteractor = new ToolUseInteractor(canvasState);

        // TODO: controller constructor messed up with merge conflict
        return new CanvasController(mouseInteractor, toolUseInteractor);
    }
}

