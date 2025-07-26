package view.MidMenuBar.ColorButtonsBar;

import view.MidMenuBar.SelectButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.JButton;

public class ColorWheelButton extends CircularButton{
    ImageIcon imageIcon;

    public ColorWheelButton(){
         super(36);
        imageIcon = new ImageIcon(
                SelectButton.class.getResource("/images/Wheel.png"));

        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);

    }

    public JButton getColorWheelButton(){
        return this;
    }
}
