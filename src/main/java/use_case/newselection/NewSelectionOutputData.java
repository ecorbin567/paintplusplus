package use_case.newselection;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public record NewSelectionOutputData(
        boolean hasSelection,
        Rectangle selectionBounds,
        BufferedImage livePreview) {
}
