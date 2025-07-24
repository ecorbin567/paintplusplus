package view.MidMenuBar.ColorButtonsBar;

import java.awt.Color;

import javax.swing.JButton;

public class UpperColorChooserButton extends JButton{
    private Color upperCurrentColor;

    public UpperColorChooserButton(Color c){
        setSelectedUpperColor(c);

    }

    public Color getUpperCurrentColor(){
        return this.upperCurrentColor;
    }
    public void setSelectedUpperColor(Color newColor){
        setSelectedUpperColor(newColor);
    }


}
