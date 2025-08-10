package app;

import entity.CanvasState;
import interface_adapter.SelectionPresenter;
import interface_adapter.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.canvas.DrawingViewModel;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.mouseui.MouseUIUseInteractor;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInteractor;
import use_case.topmenu.ImageFileSaveGateway;
import use_case.topmenu.TopMenuInputBoundary;
import use_case.topmenu.TopMenuInteractor;

public class CanvasControllerFactory {
    public static CanvasController createCanvasController(CanvasState canvasState,
                                                          DrawingViewModel drawingViewModel,
                                                          ImageFileSaveGateway saveGateway,
                                                          SelectionViewModel selectionViewModel){

        CanvasPresenter canvasPresenter = new CanvasPresenter(drawingViewModel);
        SelectionPresenter selectionPresenter = new SelectionPresenter(selectionViewModel);

        MouseUIUseInputBoundary mouseInteractor = new MouseUIUseInteractor(canvasState,
                canvasPresenter, selectionPresenter);

        TopMenuInputBoundary topMenuInteractor = new TopMenuInteractor(canvasState,
                canvasPresenter, saveGateway);

        ToolUseInputBoundary toolUseInteractor = new ToolUseInteractor(canvasState);

        return new CanvasController(mouseInteractor, toolUseInteractor,
                topMenuInteractor);
    }
}

