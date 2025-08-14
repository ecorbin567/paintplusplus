package use_case.changecolor;

import entity.CanvasState;
import entity.Paintbrush;

/**
 * The Change Color Interactor.
 */
public class ChangeColorInteractor implements ChangeColorInputBoundary {

    private final CanvasState canvasState;
    private final Paintbrush paintbrush;

    public ChangeColorInteractor(CanvasState canvasState) {
        this.canvasState = canvasState;
        this.paintbrush = canvasState.getPaintbrush();
    }

    @Override
    public void changeColor(ChangeColorInputData input) {
        // update the brush color
        paintbrush.setColour(input.getNewColor());
        // make presenter to give it to the UI
    }

    @Override
    public void setTool(ChangeColorInputData input) {
        this.canvasState.setToolState(input.getToolName());
    }

    @Override
    public void setButton(ChangeColorInputData inputData) {
        final String buttonName = inputData.getButtonName();
        this.canvasState.setButtonPressed(buttonName);
    }
}
