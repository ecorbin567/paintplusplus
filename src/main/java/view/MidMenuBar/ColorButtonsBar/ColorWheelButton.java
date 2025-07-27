package view.MidMenuBar.ColorButtonsBar;

import view.MidMenuBar.SelectionToolButton;

import javax.swing.*;
import java.awt.*;

public class ColorWheelButton extends CircularButton{
    ImageIcon imageIcon;

    public ColorWheelButton(){
        super(36);
        imageIcon = new ImageIcon(
                SelectionToolButton.class.getResource("/images/wheel.png"));

        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(36, 36, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);

    }

    public JToggleButton getColorWheelButton(){
        return this;
    }
}
