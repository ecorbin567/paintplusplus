package view.MidMenuBar.EraserButtonGroup;

import entity.DrawingCanvas;

import javax.swing.*;
import java.awt.*;

public class EraseButton {
    JButton button;
    ImageIcon imageIcon;
    DrawingCanvas canvas;

    public EraseButton(DrawingCanvas canvas) {
        this.canvas = canvas;
        ErasePopUp erasePopUp = new ErasePopUp(canvas);
        button = new JButton();
        imageIcon = new ImageIcon(EraseButton.class.getResource("/images/EraseIcon.png"));
        Image image = imageIcon.getImage();
        Image newimage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimage);
        button.setIcon(imageIcon);
        button.setPreferredSize(new Dimension(60, 60));
        button.addActionListener(evt -> canvas.erase());
        button.addActionListener(e -> {
            erasePopUp.getPopupMenu().show(button, button.getWidth(), button.getHeight());
        });
    }

    public JButton getButton() {
        return button;
    }
}

