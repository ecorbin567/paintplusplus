package view.midmenubar.colorbuttonsbar;

import entity.ToolEnum;
import interface_adapter.changecolor.ColorController;

import javax.swing.*;
import java.awt.*;

public class LowerColorChooserButton extends CircularButton{

    private Color lowerCurrentColor = Color.WHITE;
    private static final ToolEnum toolName = ToolEnum.PENCIL;
    private final String buttonName = "LowerColorChooserButton";

    public LowerColorChooserButton(ColorController colorController) {
        super(30);

        setBackground(lowerCurrentColor);
        this.addActionListener(e->
            colorController.handleColorChangeButton(toolName, lowerCurrentColor, buttonName));

    }

    public Color getLowerCurrentColor(){
        return this.lowerCurrentColor;
    }

    public void setCurrentColor(Color c){
        this.lowerCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }
}
