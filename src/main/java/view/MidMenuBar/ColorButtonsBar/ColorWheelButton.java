package view.MidMenuBar.ColorButtonsBar;

import view.MidMenuBar.SelectButton;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWheelButton extends JFrame{
    JButton colorWheelButton;
    ImageIcon imageIcon;

    public ColorWheelButton(){
        colorWheelButton = new JButton();
        imageIcon = new ImageIcon(SelectButton.class.getResource("/images/ColorWheelIcon.png"));

        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

        imageIcon = new ImageIcon(newImage);
        colorWheelButton.setIcon(imageIcon);

        colorWheelButton.setPreferredSize(new Dimension(20, 20));
        colorWheelButton.setBorder(new RoundedButton(10));

        colorWheelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorWheelPopUpWindow colorWheelPopUp = new ColorWheelPopUpWindow(ColorWheelButton.this);
                colorWheelPopUp.setVisible(true);
            }
        });
        add(colorWheelButton);
        setVisible(true);
    }

    public JButton getColorWheelButton(){
        return this.colorWheelButton;
    }
}
