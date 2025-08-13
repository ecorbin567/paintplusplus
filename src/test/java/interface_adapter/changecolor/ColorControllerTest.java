package interface_adapter.changecolor;

import entity.ToolEnum;
import org.junit.jupiter.api.Test;
import use_case.changecolor.ChangeColorInputBoundary;
import use_case.changecolor.ChangeColorInputData;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ColorControllerTest {

    @Test
    void handleColorChangeButton() {
        FakeChangeColorInteractor interactor = new FakeChangeColorInteractor();
        ColorController controller = new ColorController(interactor);

        ToolEnum tool = ToolEnum.PENCIL;
        Color color = new Color(10, 20, 30);
        String button = "fg";

        controller.handleColorChangeButton(tool, color, button);

        assertTrue(interactor.changeCalled);
        assertTrue(interactor.setToolCalled);
        assertTrue(interactor.setButtonCalled);

        // same DTO instance is passed to all three calls
        assertSame(interactor.lastChange, interactor.lastSetTool);
        assertSame(interactor.lastChange, interactor.lastSetButton);

        // payload checks (assuming typical getters on the input data)
        assertEquals(tool, interactor.lastChange.getToolName());
        assertEquals(color, interactor.lastChange.getNewColor());
        assertEquals(button, interactor.lastChange.getButtonName());
    }

    @Test
    void testHandleColorChangeButton() {
        FakeChangeColorInteractor interactor = new FakeChangeColorInteractor();
        ColorController controller = new ColorController(interactor);

        ToolEnum tool = ToolEnum.ERASER;
        Color color = Color.BLUE;

        controller.handleColorChangeButton(tool, color);

        assertTrue(interactor.changeCalled);
        assertTrue(interactor.setToolCalled);
        assertFalse(interactor.setButtonCalled);

        assertSame(interactor.lastChange, interactor.lastSetTool);

        assertEquals(tool, interactor.lastChange.getToolName());
        assertEquals(color, interactor.lastChange.getNewColor());
        // If your DTO leaves buttonName unset in this overload, this should be null.
        assertNull(interactor.lastChange.getButtonName());
    }

    private static class FakeChangeColorInteractor implements ChangeColorInputBoundary {
        boolean changeCalled;
        boolean setToolCalled;
        boolean setButtonCalled;

        ChangeColorInputData lastChange;
        ChangeColorInputData lastSetTool;
        ChangeColorInputData lastSetButton;

        @Override
        public void changeColor(ChangeColorInputData inputData) {
            changeCalled = true;
            lastChange = inputData;
        }

        @Override
        public void setTool(ChangeColorInputData inputData) {
            setToolCalled = true;
            lastSetTool = inputData;
        }

        @Override
        public void setButton(ChangeColorInputData inputData) {
            setButtonCalled = true;
            lastSetButton = inputData;
        }
    }
}
