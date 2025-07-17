package entity.MidMenuBar;

import javax.swing.*;
import java.awt.*;

public class EraseButton {
    JButton button;

    public EraseButton(){
        ImageIcon imageIcon = new ImageIcon(EraseButton.class.getResource("/images/EraseIcon.png"));
        button = new JButton(imageIcon);
        button.setPreferredSize(new Dimension(200, 80));


    }

    public JButton getButton(){
        return button;
    }

    public static void main (String[] args){
        JFrame frame = new JFrame();
        EraseButton eraseButton = new EraseButton();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.add(eraseButton.getButton());
        frame.setVisible(true);
    }
}
