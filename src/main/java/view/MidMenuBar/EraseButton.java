package view.MidMenuBar;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EraseButton implements ActionListener {
    JButton button;
    ImageIcon imageIcon;
    DrawingCanvas canvas;

    public EraseButton(DrawingCanvas canvas) {
        this.canvas = canvas;
        button = new JButton();
        imageIcon = new ImageIcon(EraseButton.class.getResource("/images/EraseIcon.png"));
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));
    }

    public JButton getButton() {
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        button.addActionListener(evt -> canvas.erase());
    }


}

