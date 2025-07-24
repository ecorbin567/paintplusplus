package view.MidMenuBar;

import java.awt.Color;

import javax.swing.JButton;

public class UpperColorWheelButton extends JButton{
    private Color upperCurrentColor;

    public UpperColorWheelButton(Color c){
        setSelectedUpperColor(c);

    }

    public Color getUpperCurrentColor(){
        return this.upperCurrentColor;
    }
    public void setSelectedUpperColor(Color newColor){
        setSelectedUpperColor(newColor);
    }


}
