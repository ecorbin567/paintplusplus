package view.MidMenuBar.ColorButtonsBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LowerColorChooserButton extends JButton{
    private Color lowerCurrentColor;
    private ColorWheelPanel colorWheelPanel;

    public LowerColorChooserButton(){
        setPreferredSize(new Dimension(30, 30));
        setBorder(new RoundedButton(20));
        setBackground(Color.white);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = colorWheelPanel.getSelectedColor();
                setBackground(color); // set the background with a new color from colorwheelpanel
                lowerCurrentColor = color;
            }
        });
        setVisible(true);
    }

    public Color getLowerCurrentColor(){
        return this.lowerCurrentColor;
    }
}
