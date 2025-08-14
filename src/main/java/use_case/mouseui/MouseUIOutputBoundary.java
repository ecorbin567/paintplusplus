package use_case.mouseui;

/**
 * The output boundary for the mouse ui use case.
 */
public interface MouseUIOutputBoundary {

    /**
     * Prepares whether the view should repaint based on the last state.
     *
     * @param outputData the new mouse ui output data.
     */
    void setRepaintState(MouseUIOutputData outputData);

    /**
     * Prepares the stack/list of drawables that represent the canvas content to render.
     *
     * @param outputData the new mouse ui output data.
     */
    void setDrawableState(MouseUIOutputData outputData);

    /**
     * Prepares the currently active Drawable object.
     *
     * @param outputData the new mouse ui output data.
     */
    void setCurrentDrawable(MouseUIOutputData outputData);
}
