package view.MidMenuBar.ColorButtonsBar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

import javax.swing.*;
import java.awt.*;

public class SingleColorButton extends CircularButton {
    private static final ToolEnum toolName = ToolEnum.PENCIL;
    public SingleColorButton(Color c, ColorController colorController,
                             UpperColorChooserButton upperColorChooserButton,
                             LowerColorChooserButton lowerColorChooserButton) {
        super(20);
        setBackground(c);
        this.addActionListener(e -> {
            colorController.handleColorChangeButton(toolName, c);
            if (upperColorChooserButton.isSelected()) {
                upperColorChooserButton.setCurrentColor(c);
            }
            else{
                lowerColorChooserButton.setCurrentColor(c);
            }
            this.setSelected(false);
        });
    }
    public AbstractButton getButton(){
        return this;
    }

//                swatch.addActionListener(e -> {
//        if (upperColorChooserButton.isSelected()){
//            primaryInteractor.changeColor(new ChangeColorInputData(solidColor));
//        } else {
//            secondaryInteractor.changeColor((new ChangeColorInputData(solidColor)));
//        }
//        swatch.setSelected(false); // clear toggle state so it doesn't stay
//    });
}
