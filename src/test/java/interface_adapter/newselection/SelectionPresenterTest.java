package interface_adapter.newselection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.newselection.NewSelectionOutputData;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SelectionPresenterTest {

    private SelectionViewModel vm;
    private SelectionPresenter presenter;

    @BeforeEach
    void setup() {
        vm = new SelectionViewModel();
        presenter = new SelectionPresenter(vm);
    }

    @Test
    void presenterSetsAllViewModelFields() {
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Rectangle selBounds = new Rectangle(5, 6, 7, 8);
        Rectangle toolBounds = new Rectangle(1, 2, 3, 4);

        NewSelectionOutputData d = new NewSelectionOutputData(
                img,
                selBounds,
                true,   // hasSelection
                true,   // draggingSelection
                true,   // isDrawing
                toolBounds
        );

        presenter.setSelectionImage(d);
        presenter.setSelectionBounds(d);
        presenter.setIsDraggingSelection(d);
        presenter.setHasSelection(d);
        presenter.setIsDrawing(d);
        presenter.setSelectionToolBounds(d);

        assertSame(img, vm.getSelectionImage(), "Image should be forwarded unchanged");
        assertEquals(selBounds, vm.getSelectionBounds(), "Selection bounds should match");
        assertTrue(vm.getDraggingSelection(), "Dragging flag should be true");
        assertTrue(vm.getHasSelection(), "Has-selection flag should be true");
        assertTrue(vm.getIsDrawing(), "Is-drawing flag should be true");
        assertEquals(toolBounds, vm.getSelectionToolBounds(), "Tool bounds should match");

        // Update again with different values to ensure overwriting works
        BufferedImage img2 = new BufferedImage(4, 4, BufferedImage.TYPE_INT_ARGB);
        Rectangle selBounds2 = new Rectangle(10, 11, 12, 13);
        Rectangle toolBounds2 = new Rectangle(7, 8, 9, 10);
        NewSelectionOutputData d2 = new NewSelectionOutputData(
                img2, selBounds2, false, false, false, toolBounds2
        );

        presenter.setSelectionImage(d2);
        presenter.setSelectionBounds(d2);
        presenter.setIsDraggingSelection(d2);
        presenter.setHasSelection(d2);
        presenter.setIsDrawing(d2);
        presenter.setSelectionToolBounds(d2);

        assertSame(img2, vm.getSelectionImage(), "Image should update");
        assertEquals(selBounds2, vm.getSelectionBounds(), "Bounds should update");
        assertFalse(vm.getDraggingSelection(), "Dragging flag should update to false");
        assertFalse(vm.getHasSelection(), "Has-selection flag should update to false");
        assertFalse(vm.getIsDrawing(), "Is-drawing flag should update to false");
        assertEquals(toolBounds2, vm.getSelectionToolBounds(), "Tool bounds should update");
    }

    @Test
    void presenterHandlesNullsGracefully() {
        // Allow null image / bounds; flags false
        NewSelectionOutputData d = new NewSelectionOutputData(
                null, null, false, false, false, null
        );

        presenter.setSelectionImage(d);
        presenter.setSelectionBounds(d);
        presenter.setIsDraggingSelection(d);
        presenter.setHasSelection(d);
        presenter.setIsDrawing(d);
        presenter.setSelectionToolBounds(d);

        assertNull(vm.getSelectionImage(), "Null image should be propagated");
        assertNull(vm.getSelectionBounds(), "Null selection bounds should be propagated");
        assertNull(vm.getSelectionToolBounds(), "Null tool bounds should be propagated");
        assertFalse(vm.getDraggingSelection(), "Dragging flag should be false");
        assertFalse(vm.getHasSelection(), "Has-selection flag should be false");
        assertFalse(vm.getIsDrawing(), "Is-drawing flag should be false");
    }
}
