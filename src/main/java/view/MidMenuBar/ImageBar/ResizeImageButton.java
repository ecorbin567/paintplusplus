package view.MidMenuBar.ImageBar;

import javax.swing.*;
import java.awt.*;

public class ResizeImageButton {
    JButton button;

    public ResizeImageButton() {
        button = new JButton();
        ImageIcon icon = new ImageIcon(ResizeImageButton.class.getResource("/images/ResizeIcon.png"));
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
