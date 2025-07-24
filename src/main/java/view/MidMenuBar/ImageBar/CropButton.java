package view.MidMenuBar.ImageBar;

import javax.swing.*;
import java.awt.*;

public class CropButton {
    JButton button;

    public CropButton() {
        button = new JButton();
        ImageIcon icon = new ImageIcon(CropButton.class.getResource("/images/CropIcon.png"));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        button.setIcon(icon);
        button.setPreferredSize(new Dimension(60, 60));
    }

    public JButton getButton() {
        return button;
    }
}
