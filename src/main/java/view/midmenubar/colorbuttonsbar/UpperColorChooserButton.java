package view.midmenubar.colorbuttonsbar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

import java.awt.*;

import javax.swing.*;

public class UpperColorChooserButton extends CircularButton{

    private Color upperCurrentColor = Color.BLACK;
    private static final ToolEnum toolName = ToolEnum.PENCIL;
    private static final String BUTTON = "UpperColorChooserButton";

    public UpperColorChooserButton(ColorController colorController){

        super(30);
        setBackground(upperCurrentColor); // default color
        this.addActionListener(e-> colorController.handleColorChangeButton(toolName,
                    upperCurrentColor, BUTTON));
    }

    public void setCurrentColor(Color c){
        this.upperCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }
}
