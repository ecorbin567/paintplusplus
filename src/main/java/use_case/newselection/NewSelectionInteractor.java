package use_case.newselection;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class NewSelectionInteractor implements NewSelectionInputBoundary {

    private final CanvasState canvasState;
    private final ActionHistory actionHistory;
    private final NewSelectionOutputBoundary output;
    private final SelectionTool tool;
    private final List<Rectangle> clearRegions;
    private final List<CanvasState.Pair<BufferedImage, Rectangle>> committedSelections;

    public NewSelectionInteractor(CanvasState canvasState,
                                  NewSelectionOutputBoundary output) {
        this.canvasState = canvasState;
        this.actionHistory = canvasState.getActionHistory();
        this.output = output;
        this.tool = canvasState.getSelectionTool();
        this.clearRegions = canvasState.getClearRegions();
        this.committedSelections = canvasState.getCommitedSelections();
    }

    @Override
    public void execute(NewSelectionInputData in) {
        // No-op unless the Selection tool is active
        if (canvasState.getToolState() != ToolEnum.SELECT) {
            return;  // don't send output here; MouseUI handles other tools
        }
        switch (in.action()) {
            case START -> onStart(in.point());
            case DRAG  -> onDrag(in.point());
            case COMMIT -> onCommit(in.point(), in.baseImage());
            case CANCEL -> onCancel();
        }
        sendSelectionOutput();
    }

    /** Mouse pressed. */
    private void onStart(Point p) {
        if (p == null) return;
        boolean hasSel = canvasState.getHasSelection();
        Rectangle sel = canvasState.getSelectionBounds();

        if (hasSel && sel != null && sel.contains(p)) {
            // Start dragging existing selection.
            canvasState.setDraggingSelection(true);
            canvasState.setHasCutOut(false);
            canvasState.setDragAnchor(new Point(p.x - sel.x, p.y - sel.y));
        } else if (hasSel) {
            // Clicked outside -> deselect (commit move if moved).
            deselect();
        } else {
            // Begin drawing a new selection box.
            canvasState.setDraggingSelection(false);
            tool.start(p);
            canvasState.setIsDrawing(true);
        }
    }

    /** Mouse dragged. */
    private void onDrag(Point p) {
        if (p == null) return;

        if (canvasState.getDraggingSelection()) {
            if (!canvasState.getHasCutOut()) {
                Rectangle hole = new Rectangle(canvasState.getSelectionBounds());
                clearRegions.add(hole);
                actionHistory.push(new CutRecord(hole));
                canvasState.setHasCutOut(true);
            }
            Rectangle sel = canvasState.getSelectionBounds();
            Point anchor = canvasState.getDragAnchor();
            if (sel != null && anchor != null) {
                sel.x = p.x - anchor.x;
                sel.y = p.y - anchor.y;
            }
        } else {
            tool.drag(p);
        }
    }

    /** Mouse released: finalize selection or end drag. */
    private void onCommit(Point p, BufferedImage base) {
        if (!canvasState.getDraggingSelection()) {
            tool.finish(p);
            canvasState.setIsDrawing(false);
            Rectangle rect = tool.getBounds();

            if (rect.width > 0 && rect.height > 0 && base != null) {
                // Guard against out-of-bounds
                Rectangle imgBounds = new Rectangle(0, 0, base.getWidth(), base.getHeight());
                Rectangle clipped = rect.intersection(imgBounds);
                if (clipped.width > 0 && clipped.height > 0) {
                    BufferedImage sub = base.getSubimage(clipped.x, clipped.y, clipped.width, clipped.height);
                    canvasState.setSelectionImage(sub);
                    canvasState.setSelectionBounds(new Rectangle(clipped));
                    canvasState.setSelectionOriginalBounds(new Rectangle(clipped));
                    canvasState.setHasSelection(true);
                } else {
                    clearSelectionState();
                }
            } else {
                clearSelectionState();
            }
        }
        canvasState.setDraggingSelection(false);
        tool.cancel();
    }

    /** Escape/cancel during draw. */
    private void onCancel() {
        clearSelectionState();
        tool.cancel();
    }

    /** Commit move if position changed; then clear selection. */
    private void deselect() {
        canvasState.setDraggingSelection(false);
        canvasState.setHasCutOut(false);

        if (actionHistory.getCurrentState() instanceof CutRecord) {
            actionHistory.undo();
        }

        Rectangle src = canvasState.getSelectionOriginalBounds();
        Rectangle dest = canvasState.getSelectionBounds();
        BufferedImage img = canvasState.getSelectionImage();

        if (img != null && src != null && dest != null && !dest.equals(src)) {
            actionHistory.push(new MoveRecord(img, new Rectangle(src), new Rectangle(dest)));
            committedSelections.add(new CanvasState.Pair<>(img, new Rectangle(dest)));
            clearRegions.add(new Rectangle(src));
        }

        clearSelectionState();
        tool.cancel();
    }

    private void clearSelectionState() {
        canvasState.setHasSelection(false);
        canvasState.setSelectionImage(null);
        canvasState.setSelectionBounds(null);
        canvasState.setSelectionOriginalBounds(null);
        canvasState.setDragAnchor(null);
        canvasState.setIsDrawing(false);
    }

    private void sendSelectionOutput() {
        NewSelectionOutputData d = new NewSelectionOutputData(
                canvasState.getSelectionImage(),
                canvasState.getSelectionBounds(),
                canvasState.getHasSelection(),
                canvasState.getDraggingSelection(),
                canvasState.getIsDrawing(),
                canvasState.getSelectionTool().getBounds()
        );
        output.setIsDraggingSelection(d);
        output.setIsDrawing(d);
        output.setSelectionToolBounds(d);
        output.setHasSelection(d);
        output.setSelectionImage(d);
        output.setSelectionBounds(d);
    }
}
