package use_case.changecolor;

import entity.Paintbrush;

/**
 * The Change Colour Interactor.
 */
public class ChangeColorInteractor implements ChangeColorInputBoundary{

    private final Paintbrush paintbrush;
    private final ChangeColorOutputBoundary presenter;

    public ChangeColorInteractor(Paintbrush paintbrush,
                                 ChangeColorOutputBoundary presenter){
        this.paintbrush = paintbrush;
        this.presenter = presenter;
    }

    @Override
    public void changeColor(ChangeColorInputData input){
        // update the brush color
        paintbrush.setColour(input.getNewColor());
        // make presenter to give it to the UI
        presenter.presentColorChanged(input.getNewColor());
    }
}
