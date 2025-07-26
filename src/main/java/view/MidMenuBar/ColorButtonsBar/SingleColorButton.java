package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;

public class SingleColorButton extends CircularButton {

    private final Color buttonColor;

    public SingleColorButton(Color c){
        super(20);
        this.buttonColor = c;
        setBackground(c);
    }
    public Color getButtonColor(){
        return this.buttonColor;
    }
}
