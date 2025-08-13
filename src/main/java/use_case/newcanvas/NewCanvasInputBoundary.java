package use_case.newcanvas;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Input Boundary for actions which are related to creating a new canvas.
 */
public interface NewCanvasInputBoundary {

    /**
     * Executes the login use case.
     * @param newCanvasInputData the input data
     */
    void execute(NewCanvasInputData newCanvasInputData);

    /**
     * Executes the login use case when importing an existing canvas.
     * @param newCanvasInputData the input data
     */
    void executeImportExistingCanvas(NewCanvasInputData newCanvasInputData);

    /**
     * Switch.
     */
    void switchToSignupView();

    /**
     * Self-explanatory.
     * @param newCanvasInputData data
     * @return list of canvases
     */
    List<BufferedImage> getUserCanvases(NewCanvasInputData newCanvasInputData);
}
