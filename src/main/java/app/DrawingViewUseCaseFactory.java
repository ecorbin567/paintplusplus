package app;

import interface_adapter.newselection.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.DrawingViewModel;
import view.DrawingView;

public class DrawingViewUseCaseFactory {
    /**
     * Factory function for creating the Drawing View Use Case.
     * @param controller the CanvasController
     * @param renderer the CanvasRenderer
     * @param selectionViewModel the SelectionViewModel
     * @param drawingViewModel the DrawingViewModel
     * @return the DrawingView created
     */
    public static DrawingView create(CanvasController controller,
                                     CanvasRenderer renderer,
                                     SelectionViewModel selectionViewModel,
                                     DrawingViewModel drawingViewModel) {

        return new DrawingView(controller, renderer, selectionViewModel, drawingViewModel);
    }
}
