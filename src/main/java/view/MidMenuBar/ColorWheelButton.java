package view.MidMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWheelButton {
    private final JButton button;
    private final ImageIcon imageIcon;

    public ColorWheelButton(){
        button = new JButton();
        imageIcon = new ImageIcon();



    }

    public JButton getButton(){
        return this.button;
    }
}
