package view.MidMenuBar;

import javax.swing.*;
import java.awt.*;

public class ImageButton {
    JButton button;
    public ImageButton() {
        button = new JButton();
        ImageIcon icon = new ImageIcon(SelectButton.class.getResource("/images/ImageIcon.png"));
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
