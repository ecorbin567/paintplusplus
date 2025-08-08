package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The output boundary for the New Canvas Use Case.
 */
public interface NewCanvasOutputBoundary {
    void prepareSuccessView(NewCanvasOutputData newCanvasOutputData);

    void switchToSignupView();
}
