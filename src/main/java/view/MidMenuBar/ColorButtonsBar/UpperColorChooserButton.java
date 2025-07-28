package view.MidMenuBar.ColorButtonsBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UpperColorChooserButton extends CircularButton{

    private Color upperCurrentColor = Color.BLACK;

    public UpperColorChooserButton(){
        super(30);
        setBackground(upperCurrentColor); // default color

    }

    public Color getUpperCurrentColor(){
        return this.upperCurrentColor;
    }

    public void setCurrentColor(Color c){
        this.upperCurrentColor = c;
        setBackground(c);
    }

    public JToggleButton getButton(){
        return this;
    }
}
