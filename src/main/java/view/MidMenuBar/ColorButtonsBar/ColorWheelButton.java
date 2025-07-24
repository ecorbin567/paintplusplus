package view.MidMenuBar.ColorButtonsBar;

import view.MidMenuBar.SelectButton;

import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorWheelButton extends JFrame{
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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorWheelPopUpWindow colorWheelPopUp = new ColorWheelPopUpWindow(ColorWheelButton.this);
                colorWheelPopUp.setVisible(true);
            }
        });
        add(button);
        setVisible(true);
    }

    public JButton getButton(){
        return this.button;
    }
}
