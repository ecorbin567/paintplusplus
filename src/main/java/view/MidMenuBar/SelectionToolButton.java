package view.MidMenuBar;

import javax.swing.*;
import java.awt.*;

public class SelectionToolButton {
    JButton button;
    public SelectionToolButton(){
        button = new JButton();
        ImageIcon icon = new ImageIcon(SelectionToolButton.class.getResource("/images/SelectIcon.png"));
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
