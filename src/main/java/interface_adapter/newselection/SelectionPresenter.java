package interface_adapter.newselection;

import use_case.newselection.NewSelectionOutputBoundary;
import use_case.mouseui.SelectOutputData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionPresenter implements NewSelectionOutputBoundary {
    SelectionViewModel selectionViewModel;
    public SelectionPresenter(SelectionViewModel selectionViewModel) {
        this.selectionViewModel = selectionViewModel;
    }

    @Override
    public void setSelectionImage(SelectOutputData selectOutputData) {
        BufferedImage image = selectOutputData.getSelectionImage();
        selectionViewModel.setBuffedImage(image);
    }

    @Override
    public void setSelectionBounds(SelectOutputData selectOutputData) {
        Rectangle bounds = selectOutputData.getSelectionBounds();
        selectionViewModel.setSelectionBounds(bounds);
    }

    @Override
    public void setIsDraggingSelection(SelectOutputData selectOutputData) {
        boolean isDragging = selectOutputData.getIsDragginSelection();
        selectionViewModel.setDraggingSelection(isDragging);
    }

    @Override
    public void setHasSelection(SelectOutputData selectOutputData) {
        boolean hasSelection = selectOutputData.getHasSelection();
        selectionViewModel.setHasSelection(hasSelection);
    }

    @Override
    public void setIsDrawing(SelectOutputData selectOutputData) {
        boolean isDrawing = selectOutputData.getIsDrawing();
        selectionViewModel.setIsDrawing(isDrawing);
    }

    @Override
    public void setSelectionToolBounds(SelectOutputData selectOutputData) {
        Rectangle bounds = selectOutputData.getSelectionToolBounds();
        selectionViewModel.setSelectionToolBounds(bounds);
    }
}
