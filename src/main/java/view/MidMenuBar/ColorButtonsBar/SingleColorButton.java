package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;

public class SingleColorButton extends JButton {

    private Color buttonColor;

    public SingleColorButton(Color c){
        this.buttonColor = c;
    }
    public Color getButtonColor(){
        return this.buttonColor;
    }

}
