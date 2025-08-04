package interface_adapter.canvas;

import entity.SelectionTool;
import entity.DrawingCanvas;
import use_case.newselection.*;

import java.awt.Point;
import java.awt.event.*;

public class CanvasController
        implements MouseListener, MouseMotionListener {

    private final NewSelectionInputBoundary selectionUC;
    private final DrawingCanvas canvas;
    private final SelectionTool tool;

    public CanvasController(DrawingCanvas canvas,
                            SelectionTool tool,
                            NewSelectionOutputBoundary presenter) {

        this.canvas = canvas;
        this.tool   = tool;
        this.selectionUC =
                new NewSelectionInteractor(canvas, tool, presenter);

        // register listeners on the canvas view
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }

    /* -------- MouseListener / MotionListener ---------- */
    private boolean selectionMode(){
        return "Selection".equals(canvas.getSelectedTool());
    }

    @Override public void mousePressed (MouseEvent e) {
        if (!selectionMode()) return;
        selectionUC.execute(
                new NewSelectionInputData(
                        NewSelectionInputData.SelectionAction.START,
                        e.getX(), e.getY(), 0, 0));
    }

    @Override public void mouseDragged (MouseEvent e) {
        if (!selectionMode()) return;
        selectionUC.execute(
                new NewSelectionInputData(
                        NewSelectionInputData.SelectionAction.DRAG,
                        e.getX(), e.getY(), 0, 0));
    }

    @Override public void mouseReleased(MouseEvent e) {
        if (!selectionMode()) return;
        selectionUC.execute(
                new NewSelectionInputData(
                        NewSelectionInputData.SelectionAction.COMMIT,
                        e.getX(), e.getY(), 0, 0));
    }

    /* unused but required */
    @Override public void mouseClicked (MouseEvent e) {}
    @Override public void mouseEntered (MouseEvent e) {}
    @Override public void mouseExited  (MouseEvent e) {}
    @Override public void mouseMoved   (MouseEvent e) {}
}
