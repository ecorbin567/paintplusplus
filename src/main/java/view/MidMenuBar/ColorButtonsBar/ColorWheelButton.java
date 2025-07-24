package view.MidMenuBar.ColorButtonsBar;

import view.MidMenuBar.SelectButton;

import javax.swing.*;
import java.awt.*;

public class ColorWheelButton {
    JButton button;
    ImageIcon imageIcon;

    public ColorWheelButton(){
        button = new JButton();
        imageIcon = new ImageIcon(SelectButton.class.getResource("/images/ColorWheelIcon.png"));

        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        button.setIcon(imageIcon);

        button.setPreferredSize(new Dimension(20, 20));
        button.setBorder(new RoundedButton(10));

    }

    public JButton getButton(){
        return this.button;
    }
}
