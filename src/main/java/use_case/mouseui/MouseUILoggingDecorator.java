package use_case.mouseui;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Logs calls and execution times for a MouseUIUseInputBoundary.
 * Uses java.util.logging with parameterized messages.
 */
public final class MouseUILoggingDecorator implements MouseUIUseInputBoundary {
    private static final Logger LOG = Logger.getLogger(MouseUILoggingDecorator.class.getName());
    private final MouseUIUseInputBoundary inner;

    /**
     * Creates a logging decorator that wraps an existing interactor.
     *
     * @param inner the interactor to wrap
     */
    public MouseUILoggingDecorator(MouseUIUseInputBoundary inner) {
        this.inner = inner;
    }

    /**
     * Logs a mouse press event and how long it takes to process.
     *
     * @param inputData the mouse inputData data
     */
    @Override
    public void mouseIsPressed(MouseUIInputData inputData) {
        long start = System.nanoTime();
        LOG.log(Level.FINE, "mouseIsPressed: point={0}",
                inputData != null ? inputData.getPoint() : null);
        inner.mouseIsPressed(inputData);
        LOG.log(Level.INFO, "mouseIsPressed completed in {0} ns",
                System.nanoTime() - start);
    }

    /**
     * Logs a mouse drag event and how long it takes to process.
     *
     * @param inputData the mouse inputData data
     */
    @Override
    public void mouseIsDragged(MouseUIInputData inputData) {
        long start = System.nanoTime();
        LOG.log(Level.FINER, "mouseIsDragged: point={0}",
                inputData != null ? inputData.getPoint() : null);
        inner.mouseIsDragged(inputData);
        LOG.log(Level.FINE, "mouseIsDragged completed in {0} ns",
                System.nanoTime() - start);
    }

    /**
     * Logs a mouse release event and how long it takes to process.
     *
     * @param inputData the mouse inputData data
     */
    @Override
    public void mouseIsReleased(MouseUIInputData inputData) {
        long start = System.nanoTime();
        LOG.log(Level.FINE, "mouseIsReleased: point={0}",
                inputData != null ? inputData.getPoint() : null);
        inner.mouseIsReleased(inputData);
        LOG.log(Level.INFO, "mouseIsReleased completed in {0} ns",
                System.nanoTime() - start);
    }
}
