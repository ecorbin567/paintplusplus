package interface_adapter.presenter;

import entity.DrawingCanvas;
import use_case.newselection.NewSelectionOutputBoundary;
import use_case.newselection.NewSelectionOutputData;

public class NewSelectionPresenter implements NewSelectionOutputBoundary {

    private final DrawingCanvas canvas;

    public NewSelectionPresenter(DrawingCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void present(NewSelectionOutputData data) {
        // right now a repaint is enough; expand later if you need status text
        canvas.repaint();
    }
}