package use_case.changecolor;

import entity.CanvasState;

/**
 * The Change Colour Interactor.
 */
public class ChangeColorInteractor implements ChangeColorInputBoundary{

    private final ChangeColorOutputBoundary presenter;
    private final CanvasState canvasState;

    public ChangeColorInteractor(CanvasState canvasState,
                                 ChangeColorOutputBoundary presenter){
        this.canvasState = canvasState;
        this.presenter = presenter;
    }

    @Override
    public void changeColor(ChangeColorInputData input){
        // update the brush color by sending it to canvasstate
        canvasState.setPaintBrushColor(input.getNewColor());
        // make presenter to give it to the UI
        presenter.presentColorChanged(input.getNewColor());
    }
}
