package interface_adapter.newselection;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import use_case.newselection.NewSelectionOutputBoundary;
import use_case.newselection.NewSelectionOutputData;

public class SelectionPresenter implements NewSelectionOutputBoundary {
    SelectionViewModel selectionViewModel;

    public SelectionPresenter(SelectionViewModel selectionViewModel) {
        this.selectionViewModel = selectionViewModel;
    }

    @Override
    public void setSelectionImage(NewSelectionOutputData selectOutputData) {
        final BufferedImage image = selectOutputData.getSelectionImage();
        selectionViewModel.setBuffedImage(image);
    }

    @Override
    public void setSelectionBounds(NewSelectionOutputData selectOutputData) {
        final Rectangle bounds = selectOutputData.getSelectionBounds();
        selectionViewModel.setSelectionBounds(bounds);
    }

    @Override
    public void setIsDraggingSelection(NewSelectionOutputData selectOutputData) {
        final boolean isDragging = selectOutputData.getIsDraggingSelection();
        selectionViewModel.setDraggingSelection(isDragging);
    }

    @Override
    public void setHasSelection(NewSelectionOutputData selectOutputData) {
        final boolean hasSelection = selectOutputData.getHasSelection();
        selectionViewModel.setHasSelection(hasSelection);
    }

    @Override
    public void setIsDrawing(NewSelectionOutputData selectOutputData) {
        final boolean isDrawing = selectOutputData.getIsDrawing();
        selectionViewModel.setIsDrawing(isDrawing);
    }

    @Override
    public void setSelectionToolBounds(NewSelectionOutputData selectOutputData) {
        final Rectangle bounds = selectOutputData.getSelectionToolBounds();
        selectionViewModel.setSelectionToolBounds(bounds);
    }
}
