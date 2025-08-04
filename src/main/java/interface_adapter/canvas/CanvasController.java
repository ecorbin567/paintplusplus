package interface_adapter.canvas;

import entity.SelectionTool;
import entity.DrawingCanvas;
import use_case.newselection.*;

import java.awt.Point;
import java.awt.event.*;

public class CanvasController implements MouseListener, MouseMotionListener {

    private final NewSelectionInputBoundary selectionUC;
    private final DrawingCanvas canvas;
    private final SelectionTool tool;

    private boolean draggingExisting = false;   // are we moving a pasted image?
    private Point   lastMouse        = null;    // last mouse position

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
        return canvas.isSelectionMode();
    }

    @Override public void mousePressed (MouseEvent e) {
        if (!selectionMode()) return;
        Point p = e.getPoint();
        // clicked inside an created selection box
        if (canvas.hasCommittedSelection() && canvas.pointInSelection(p)) {
            /* user grabbed the pasted bitmap — start dragging */
            draggingExisting = true;
            lastMouse = p;                // anchor for deltas
        } else if (canvas.hasCommittedSelection()){ // click outside the selected bitmap
            selectionUC.execute(new NewSelectionInputData(
                    NewSelectionInputData.SelectionAction.START,
                    p.x, p.y, 0, 0));
        } else { // otherwise begin new selection
            draggingExisting = false;
            lastMouse        = null;
            selectionUC.execute(new NewSelectionInputData(
                    NewSelectionInputData.SelectionAction.START,
                    p.x, p.y, 0, 0));
        }
    }

    @Override public void mouseDragged (MouseEvent e) {
        if (!selectionMode()) return;
        Point p = e.getPoint();

        if (draggingExisting) {
            int dx = p.x - lastMouse.x;
            int dy = p.y - lastMouse.y;
            lastMouse = p;

            selectionUC.execute(new NewSelectionInputData(
                    NewSelectionInputData.SelectionAction.DRAG_EXISTING,
                    0, 0, dx, dy));                 // send delta only
        } else {
            selectionUC.execute(new NewSelectionInputData(
                    NewSelectionInputData.SelectionAction.DRAG,
                    p.x, p.y, 0, 0));
        }
    }

    @Override public void mouseReleased(MouseEvent e) {
        if (!selectionMode()) return;

        if (draggingExisting) {          // finished moving bitmap
            draggingExisting = false;
            return;                      // no further UC call needed
        }

        selectionUC.execute(new NewSelectionInputData(
                NewSelectionInputData.SelectionAction.COMMIT,
                e.getX(), e.getY(), 0, 0));
    }

    /* unused but required */
    @Override public void mouseClicked (MouseEvent e) {}
    @Override public void mouseEntered (MouseEvent e) {}
    @Override public void mouseExited  (MouseEvent e) {}
    @Override public void mouseMoved   (MouseEvent e) {}
}
