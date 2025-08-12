package interface_adapter.newselection;

import use_case.newselection.NewSelectionOutputBoundary;
import use_case.newselection.NewSelectionOutputData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionPresenter implements NewSelectionOutputBoundary {
    SelectionViewModel selectionViewModel;
    public SelectionPresenter(SelectionViewModel selectionViewModel) {
        this.selectionViewModel = selectionViewModel;
    }

    @Override
    public void setSelectionImage(NewSelectionOutputData selectOutputData) {
        BufferedImage image = selectOutputData.getSelectionImage();
        selectionViewModel.setBuffedImage(image);
    }

    @Override
    public void setSelectionBounds(NewSelectionOutputData selectOutputData) {
        Rectangle bounds = selectOutputData.getSelectionBounds();
        selectionViewModel.setSelectionBounds(bounds);
    }

    @Override
    public void setIsDraggingSelection(NewSelectionOutputData selectOutputData) {
        boolean isDragging = selectOutputData.getIsDraggingSelection();
        selectionViewModel.setDraggingSelection(isDragging);
    }

    @Override
    public void setHasSelection(NewSelectionOutputData selectOutputData) {
        boolean hasSelection = selectOutputData.getHasSelection();
        selectionViewModel.setHasSelection(hasSelection);
    }

    @Override
    public void setIsDrawing(NewSelectionOutputData selectOutputData) {
        boolean isDrawing = selectOutputData.getIsDrawing();
        selectionViewModel.setIsDrawing(isDrawing);
    }

    @Override
    public void setSelectionToolBounds(NewSelectionOutputData selectOutputData) {
        Rectangle bounds = selectOutputData.getSelectionToolBounds();
        selectionViewModel.setSelectionToolBounds(bounds);
    }
}
