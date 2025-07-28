package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowerColorChooserButton extends CircularButton{

    private Color lowerCurrentColor = Color.WHITE;

    public LowerColorChooserButton(){
        super(30);

        setBackground(lowerCurrentColor);

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
