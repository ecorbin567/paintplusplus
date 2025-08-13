package use_case.selection;

import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.newselection.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionInteractorTest {

    private CanvasState cs;
    private ActionHistory history;
    private NewSelectionInteractor interactor;

    // dumb presenter – we don't assert on presenter calls here
    private static class NoopPresenter implements NewSelectionOutputBoundary {
        @Override public void setSelectionImage(NewSelectionOutputData d) {}
        @Override public void setSelectionBounds(NewSelectionOutputData d) {}
        @Override public void setIsDraggingSelection(NewSelectionOutputData d) {}
        @Override public void setHasSelection(NewSelectionOutputData d) {}
        @Override public void setIsDrawing(NewSelectionOutputData d) {}
        @Override public void setSelectionToolBounds(NewSelectionOutputData d) {}
    }

    @BeforeEach
    void setUp() {
        cs = new CanvasState();
        cs.setToolState(ToolEnum.SELECT);
        history = cs.getActionHistory();
        interactor = new NewSelectionInteractor(cs, new NoopPresenter());
    }

    private static BufferedImage baseImage(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.BLACK);
        g.drawLine(10, 10, w - 10, h - 10);
        g.dispose();
        return img;
    }

    @Test
    void newValidSelection_setsImageBoundsAndFlags() {
        BufferedImage base = baseImage(200, 200);

        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START, new Point(20, 30), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,  new Point(80, 90),  null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT,new Point(80, 90),  base));

        assertTrue(cs.getHasSelection());
        assertNotNull(cs.getSelectionImage());
        Rectangle b = cs.getSelectionBounds();
        assertNotNull(b);
        assertEquals(new Rectangle(20, 30, 60, 60), b);
        assertFalse(cs.getIsDrawing());
        assertFalse(cs.getDraggingSelection());

        // creating a selection should NOT push a CutRecord
        assertFalse(history.getCurrentState() instanceof CutRecord);
    }

    @Test
    void firstDragCreatesSingleCut_regrabDoesNotAddMore() {
        // create selection
        BufferedImage base = baseImage(200, 200);
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(20, 30), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(60, 70), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(60, 70), base));

        // first drag: should create one CutRecord
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(25, 35), null)); // inside
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(100, 120), null));
        assertTrue(history.getCurrentState() instanceof CutRecord);
        int sizeAfterFirstCut = history.getUndoStack().size();

        // mouse release (commit) while dragging existing selection -> does not change history type
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(100, 120), null));

        // re-grab and drag again: should NOT create another CutRecord
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(100, 120), null)); // inside again
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(140, 150), null));

        assertTrue(history.getCurrentState() instanceof CutRecord, "still the same temporary CutRecord");
        assertEquals(sizeAfterFirstCut, history.getUndoStack().size(), "no extra cuts added");
        assertEquals(1, countCuts(history), "exactly one CutRecord exists");
    }

    @Test
    void deselectCommitsMoveRecordAndClearsHole() {
        // create selection
        BufferedImage base = baseImage(200, 200);
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(20, 30), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(60, 70), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(60, 70), base));

        // move it once (creates CutRecord)
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(25, 35), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(100, 120), null));

        // click outside -> deselect: should undo CutRecord and push MoveRecord
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(5, 5), null));

        assertFalse(cs.getHasSelection(), "selection cleared");
        assertTrue(history.getCurrentState() instanceof MoveRecord, "move committed");
        assertEquals(0, countCuts(history), "temporary cut removed");
        assertFalse(cs.getClearRegions().isEmpty(), "source region recorded");
        assertFalse(cs.getCommitedSelections().isEmpty(), "destination committed");
    }

    @Test
    void outOfBoundsSelection_isClippedInCommit() {
        BufferedImage base = baseImage(200, 200);
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(180, 180), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(240, 260), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(240, 260), base));

        assertTrue(cs.getHasSelection());
        Rectangle b = cs.getSelectionBounds();
        assertNotNull(b);
        // should be clipped to the image edge
        assertEquals(new Rectangle(180, 180, 20, 20), b);
        assertEquals(20, cs.getSelectionImage().getWidth());
        assertEquals(20, cs.getSelectionImage().getHeight());
    }

    @Test
    void zeroAreaSelection_resultsInNoSelection() {
        BufferedImage base = baseImage(200, 200);
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(50, 50), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(50, 50), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(50, 50), base));

        assertFalse(cs.getHasSelection());
        assertNull(cs.getSelectionImage());
        assertNull(cs.getSelectionBounds());
    }

    // ---- helpers ----
    private static int countCuts(ActionHistory h) {
        int n = 0;
        Drawable cur = h.getCurrentState();
        if (cur instanceof CutRecord) n++;
        Deque<Drawable> s = h.getUndoStack();
        for (Drawable d : s){
            if (d instanceof CutRecord){
                n++;
            }
        }
        return n;
    }
}
