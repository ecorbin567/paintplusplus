package interface_adapter.changecolor;

import entity.Paintbrush;
import use_case.changecolor.ChangeColorOutputBoundary;
import view.MidMenuBar.ColorButtonsBar.LowerColorChooserButton;
import view.MidMenuBar.ColorButtonsBar.UpperColorChooserButton;

import java.awt.*;

/**
 * The Presenter for the Change Colour Use Case.
 */
public class ChangeColorPresenter implements ChangeColorOutputBoundary {

    private final Paintbrush paintbrush;
    private final UpperColorChooserButton upperBtn;
    private final LowerColorChooserButton lowerBtn;
    private final boolean isPrimary;

    public ChangeColorPresenter(Paintbrush paintbrush, UpperColorChooserButton upperBtn,
                                LowerColorChooserButton lowerBtn, boolean isPrimary){
        this.paintbrush = paintbrush;
        this.upperBtn = upperBtn;
        this.lowerBtn = lowerBtn;
        this.isPrimary = isPrimary;
    }
    @Override
    public void presentColorChanged(Color color){
        paintbrush.setColour(color);
        if (isPrimary){
            upperBtn.setCurrentColor(color);
        } else {
            lowerBtn.setCurrentColor(color);
        }
    }
}
