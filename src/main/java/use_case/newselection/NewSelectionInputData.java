package use_case.newselection;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @param point     mouse position (nullable for CANCEL)
 * @param baseImage required for COMMIT to take subimage
 */
public record NewSelectionInputData(use_case.newselection.NewSelectionInputData.Action action, Point point,
                                    BufferedImage baseImage) {
    public enum Action {START, DRAG, COMMIT, CANCEL}

}
