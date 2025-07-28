package use_case.changecolor;

import entity.Paintbrush;
import view.MidMenuBar.ColorButtonsBar.LowerColorChooserButton;
import view.MidMenuBar.ColorButtonsBar.UpperColorChooserButton;

import java.awt.*;

public class ChangeColorPresenter implements ChangeColorOutputBoundary{

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
