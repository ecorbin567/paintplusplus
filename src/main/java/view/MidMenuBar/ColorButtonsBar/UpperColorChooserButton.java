package view.MidMenuBar.ColorButtonsBar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

import java.awt.*;

import javax.swing.*;

public class UpperColorChooserButton extends CircularButton{

    private Color upperCurrentColor = Color.BLACK;
    private static final ToolEnum toolName = ToolEnum.PENCIL;
    private static final String buttonName = "UpperColorChooserButton";

    public UpperColorChooserButton(ColorController colorController){

        super(30);
        setBackground(upperCurrentColor); // default color
        this.addActionListener(e-> colorController.handleColorChangeButton(toolName,
                    upperCurrentColor, buttonName));
    }

    public void setCurrentColor(Color c){
        this.upperCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }

//                upperChooserButton.addActionListener(e -> {
//        // immediately set brush to whatever color the upper chooser is showing:
//        primaryInteractor.changeColor(
//                new ChangeColorInputData(
//                        upperChooserButton.getUpperCurrentColor()
//                )
//        );
//    });
}
