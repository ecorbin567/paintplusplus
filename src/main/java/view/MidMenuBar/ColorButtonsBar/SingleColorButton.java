package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;

public class SingleColorButton extends CircularButton {

    public SingleColorButton(Color c){
        super(20);
        setBackground(c);
    }
    public AbstractButton getButton(){
        return this;
    }
}
