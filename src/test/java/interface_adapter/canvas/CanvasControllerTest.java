package interface_adapter.canvas;

import entity.ToolEnum;
import org.junit.jupiter.api.Test;
import use_case.mouseui.MouseUIInputData;
import use_case.mouseui.MouseUIUseInputBoundary;
import use_case.newselection.NewSelectionInputBoundary;
import use_case.newselection.NewSelectionInputData;
import use_case.tooluse.ToolUseInputBoundary;
import use_case.tooluse.ToolUseInputData;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class CanvasControllerTest {

    @Test
    void handlePencilButtonPress() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        controller.handleTools(ToolEnum.PENCIL);

        assertTrue(tool.setToolCalled);
        assertEquals(ToolEnum.PENCIL, tool.lastSetTool.getToolName());
        assertFalse(tool.setSizeCalled);
    }

    @Test
    void handlePencilSliderButton() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        controller.handleSlider(ToolEnum.PENCIL, 5.5f);

        assertTrue(tool.setToolCalled);
        assertTrue(tool.setSizeCalled);
        assertEquals(ToolEnum.PENCIL, tool.lastSetTool.getToolName());
        assertEquals(ToolEnum.PENCIL, tool.lastSetSize.getToolName());
        assertEquals(5.5f, tool.lastSetSize.getSize(), 1e-6);
    }

    @Test
    void handleEraserButtonPress() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        controller.handleTools(ToolEnum.ERASER);

        assertTrue(tool.setToolCalled);
        assertEquals(ToolEnum.ERASER, tool.lastSetTool.getToolName());
        assertFalse(tool.setSizeCalled);
    }

    @Test
    void handleEraserSliderButton() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        controller.handleSlider(ToolEnum.ERASER, 9.25f);

        assertTrue(tool.setToolCalled);
        assertTrue(tool.setSizeCalled);
        assertEquals(ToolEnum.ERASER, tool.lastSetTool.getToolName());
        assertEquals(ToolEnum.ERASER, tool.lastSetSize.getToolName());
        assertEquals(9.25f, tool.lastSetSize.getSize(), 1e-6);
    }

    @Test
    void handleMousePressed() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        Point p = new Point(10, 20);
        controller.handleMousePressed(p);

        assertTrue(sel.called);

        assertTrue(mouse.pressedCalled);
        assertNotNull(mouse.lastPressed);
        assertEquals(p, mouse.lastPressed.getPoint());
        assertNull(mouse.lastPressed.getImage());
    }

    @Test
    void handleMouseDragged() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        Point p = new Point(5, 6);
        controller.handleMouseDragged(p);

        assertTrue(sel.called);

        assertTrue(mouse.draggedCalled);
        assertNotNull(mouse.lastDragged);
        assertEquals(p, mouse.lastDragged.getPoint());
        assertNull(mouse.lastDragged.getImage());
    }

    @Test
    void handleMouseReleased() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        Point p = new Point(1, 2);
        BufferedImage img = new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB);
        controller.handleMouseReleased(p, img);

        assertTrue(sel.called);

        assertTrue(mouse.releasedCalled);
        assertNotNull(mouse.lastReleased);
        assertEquals(p, mouse.lastReleased.getPoint());
        assertSame(img, mouse.lastReleased.getImage());
    }

    @Test
    void handleSelectButtonPress() {
        FakeMouseUI mouse = new FakeMouseUI();
        FakeToolUse tool = new FakeToolUse();
        FakeSelection sel = new FakeSelection();

        CanvasController controller = new CanvasController(mouse, tool, sel);
        controller.handleTools(ToolEnum.SELECT);

        assertTrue(tool.setToolCalled);
        assertEquals(ToolEnum.SELECT, tool.lastSetTool.getToolName());
        assertFalse(tool.setSizeCalled);
    }

    private static class FakeMouseUI implements MouseUIUseInputBoundary {
        boolean pressedCalled, draggedCalled, releasedCalled;
        MouseUIInputData lastPressed, lastDragged, lastReleased;

        @Override
        public void mouseIsPressed(MouseUIInputData inputData) {
            pressedCalled = true;
            lastPressed = inputData;
        }

        @Override
        public void mouseIsDragged(MouseUIInputData inputData) {
            draggedCalled = true;
            lastDragged = inputData;
        }

        @Override
        public void mouseIsReleased(MouseUIInputData inputData) {
            releasedCalled = true;
            lastReleased = inputData;
        }
    }

    private static class FakeToolUse implements ToolUseInputBoundary {
        boolean setToolCalled, setSizeCalled;
        ToolUseInputData lastSetTool, lastSetSize;

        @Override
        public void setTool(ToolUseInputData inputData) {
            setToolCalled = true;
            lastSetTool = inputData;
        }

        @Override
        public void setSize(ToolUseInputData inputData) {
            setSizeCalled = true;
            lastSetSize = inputData;
        }
    }

    private static class FakeSelection implements NewSelectionInputBoundary {
        boolean called;
        NewSelectionInputData last;

        @Override
        public void execute(NewSelectionInputData inputData) {
            called = true;
            last = inputData;
        }
    }
}
