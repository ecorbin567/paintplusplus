package use_case.tooluse;

import entity.CanvasState;
import entity.ToolEnum;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ToolUseInteractorTest {

    @Test
    void setTool() {
        CanvasState canvasState = new CanvasState();
        ToolUseInteractor interactor = new ToolUseInteractor(canvasState);

        canvasState.setToolState(ToolEnum.SELECT);

        Set<ToolEnum> allowed = EnumSet.of(ToolEnum.PENCIL, ToolEnum.ERASER, ToolEnum.SELECT);
        for (ToolEnum t : allowed) {
            interactor.setTool(new ToolUseInputData(t, 0f)); // size unused
            assertEquals(t, canvasState.getToolState());
        }

        ToolEnum baseline = ToolEnum.SELECT;
        canvasState.setToolState(baseline);
        for (ToolEnum t : ToolEnum.values()) {
            if (!allowed.contains(t)) {
                interactor.setTool(new ToolUseInputData(t, 0f));
                assertEquals(baseline, canvasState.getToolState());
            }
        }
    }

    @Test
    void setSize() {
        CanvasState canvasState = new CanvasState();
        ToolUseInteractor interactor = new ToolUseInteractor(canvasState);

        // establish distinct starting sizes
        canvasState.setPaintBrushSize(3.0f);
        canvasState.setEraserSize(7.0f);

        // Pencil updates brush width only
        interactor.setSize(new ToolUseInputData(ToolEnum.PENCIL, 5.5f));
        assertEquals(5.5f, canvasState.getPaintbrush().getWidth());
        assertEquals(7.0f, canvasState.getEraser().getWidth());

        // Eraser updates eraser width only
        interactor.setSize(new ToolUseInputData(ToolEnum.ERASER, 9.25f));
        assertEquals(5.5f, canvasState.getPaintbrush().getWidth());
        assertEquals(9.25f, canvasState.getEraser().getWidth());

        // Non-sizing tool (SELECT): no changes
        interactor.setSize(new ToolUseInputData(ToolEnum.SELECT, 12.0f)); // ignored
        assertEquals(5.5f, canvasState.getPaintbrush().getWidth());
        assertEquals(9.25f, canvasState.getEraser().getWidth());
    }
}
