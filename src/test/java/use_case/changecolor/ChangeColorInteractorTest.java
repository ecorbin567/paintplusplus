package use_case.changecolor;

import entity.CanvasState;
import entity.ToolEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeColorInteractorTest {

    // Spy to observe button writes without a getter on the entity
    static class SpyCanvasState extends CanvasState {
        String lastButton;
        @Override
        public void setButtonPressed(String buttonName) {
            lastButton = buttonName;          // record for assertions
            super.setButtonPressed(buttonName);
        }
    }

    private SpyCanvasState canvasState;
    private ChangeColorInteractor interactor;

    @BeforeEach
    void setUp() {
        canvasState = new SpyCanvasState();
        interactor = new ChangeColorInteractor(canvasState);
    }

    @Test
    void changeColor_setsPaintbrushColour_forSolidColor() {
        Color target = Color.BLUE; // simulate clicking a solid color swatch
        interactor.changeColor(new ChangeColorInputData(ToolEnum.CHANGECOLOR, target));

        assertEquals(target, canvasState.getPaintbrush().getColour(),
                "Paintbrush color should match selected solid swatch");
    }

    @Test
    void changeColor_setsPaintbrushColour_forCustomWheelColor() {
        Color wheel = new Color(123, 45, 200); // simulate choosing from color wheel
        interactor.changeColor(new ChangeColorInputData(ToolEnum.CHANGECOLOR, wheel));

        assertEquals(wheel, canvasState.getPaintbrush().getColour(),
                "Paintbrush color should match arbitrary wheel color");
    }

    @Test
    void setTool_updatesCanvasStateTool() {
        interactor.setTool(new ChangeColorInputData(ToolEnum.CHANGECOLOR, Color.BLACK));

        assertEquals(ToolEnum.CHANGECOLOR, canvasState.getToolState(),
                "Tool should switch to CHANGECOLOR");
    }

    @Test
    void setButton_delegatesToCanvasState_withoutGetter() {
        String btn = "LowerColorChooserButton";
        interactor.setButton(new ChangeColorInputData(ToolEnum.CHANGECOLOR, Color.BLACK, btn));

        assertEquals(btn, canvasState.lastButton,
                "Interactor should delegate button press to CanvasState");
    }

    @Test
    void changeColor_doesNotMutateToolOrButton() {
        // Pre-set some state
        canvasState.setToolState(ToolEnum.PENCIL);
        canvasState.setButtonPressed("UpperColorChooserButton");
        ToolEnum toolBefore = canvasState.getToolState();
        String btnBefore = canvasState.lastButton;

        // Change only the color
        Color newC = new Color(10, 20, 30);
        interactor.changeColor(new ChangeColorInputData(ToolEnum.CHANGECOLOR, newC));

        // Color changed
        assertEquals(newC, canvasState.getPaintbrush().getColour());
        // Tool + button unchanged
        assertEquals(toolBefore, canvasState.getToolState());
        assertEquals(btnBefore, canvasState.lastButton);
    }
}
