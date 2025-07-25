package view.MidMenuBar.ColorButtonsBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UpperColorChooserButton extends JButton{
    private Color upperCurrentColor;
    private ColorWheelPanel colorWheelPanel;

    public UpperColorChooserButton(){
        setPreferredSize(new Dimension(30, 30));
        setBorder(new RoundedButton(20));
        setBackground(Color.black);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackground(colorWheelPanel.getSelectedColor());
            }
        });
        setVisible(true);
    }


    public Color getUpperCurrentColor(){
        return this.upperCurrentColor;
    }
}
